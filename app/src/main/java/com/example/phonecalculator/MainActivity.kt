package com.example.phonecalculator

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), CalculatorListener {

    // convert to double when calculating -> easy to manage
    private var operand1: String = ""
    private var operand2: String = ""
    private var operator:String = ""

    private var decimalOn1 = false
    private var decimalOn2 = false

    private val roundingTo = 5


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
       //

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }

    override fun onButtonClicked(buttonText: String) {
        when (buttonText) {
            in "0".."9" -> handleNumber(buttonText)
            "." -> handleDecimal()
            in listOf("+", "—", "X", "/", "%") -> handleOperator(buttonText)
            "=" -> handleEquals()
            "C" -> handleClear() // clear everything
            "CE" -> handleClearEntry() // just the last entry
        }
    }

    private fun handleNumber(num: String) {
        if (operator.isEmpty()) {
            if (decimalOn1) {
                operand1 += num
            } else {
                operand1 = operand1 + num
            }
            updateDisplay(operand1)
        } else {
            if (decimalOn2) {
                operand2 += num
            } else {
                operand2 = operand2 + num
            }
            updateDisplay(operand2)
        }
    }
    private fun handleDecimal() {
        if (operator.isEmpty() && !decimalOn1) {
            operand1 += "."
            decimalOn1 = true
            updateDisplay(operand1)
        } else if (!decimalOn2) {
            operand2 += "."
            decimalOn2 = true
            updateDisplay(operand2)
        }
    }
    private fun handleOperator(op: String) {
        if (operand1.isNotEmpty()) {
            operator = op
            decimalOn1 = false // recorded already, so set it to false
            decimalOn2 = false
        }
    }


    private fun handleEquals() {
        if (operand1.isNotEmpty() && operand2.isNotEmpty() && operator.isNotEmpty()) {
            val result = when (operator) {
                "+" -> operand1.toDouble() + operand2.toDouble()
                "—" -> operand1.toDouble() - operand2.toDouble()
                "X" -> operand1.toDouble() * operand2.toDouble()
                "/" -> if (operand2.toDouble() != 0.0) operand1.toDouble() / operand2.toDouble() else Double.NaN // check to see if the user is not clicking divide before the first operand
                "%" -> operand1.toDouble() % operand2.toDouble()
                else -> 0.0
            }
            val resultString = if (result == result.toInt().toDouble()) {
                result.toInt().toString()
            } else {
                String.format("%.${roundingTo}f", result)    // rounds the number after the decimal

                // result.toString()
            }
            updateDisplay(resultString)
            operand1 = resultString
            operand2 = ""
            operator = ""
            decimalOn1 = resultString.contains(".")
            decimalOn2 = false
        }
    }


    private fun handleClear() {
        operand1 = ""
        operand2 = ""
        operator = ""
        decimalOn1 = false
        decimalOn2 = false
        updateDisplay("0")
    }

    private fun handleClearEntry() {
        if (operator.isEmpty()) {
            operand1 = ""
            decimalOn1 = false
        } else {
            operand2 = ""
            decimalOn2 = false
        }
        updateDisplay("0")
    }


    private fun updateDisplay(text: String) {
        val displayFragment = supportFragmentManager.findFragmentById(R.id.display_fragment_container) as DisplayFragment
        displayFragment.updateDisplayText(text)
    }
/*    override fun onButtonClicked(buttonText: String){
        if (buttonText.toInt() != null)
        {
            if(buttonText.toInt() >-1 and buttonText.toInt() <10)
            {
                handleNumber(buttonText)
            }
        }
        else {
            val operators = listOf("+", "-", "*", "/", "%")
            for(String: operator in operators)
            {
                if (buttonText == operator) -> handleNumber(buttonText)
            }
            "=" -> handleEquals()
            "C" -> handleClear
            "CE" -> handleClearEntry()
        }
    }
    ChatGPT: Improve my method for my calculator

    */


}