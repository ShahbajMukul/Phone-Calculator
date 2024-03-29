package com.example.phonecalculator

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.phonecalculator.databinding.FragmentButtonsBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private lateinit var calculatorListener: CalculatorListener


class ButtonsFragment : Fragment() {

    private var _binding: FragmentButtonsBinding? = null
    private val binding get() = _binding!!

    private lateinit var calculatorListener: CalculatorListener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // check if in landscape or portrait and show appropriate layout for buttons
        val layoutResId = if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            R.layout.fragment_buttons
        } else {
            R.layout.fragment_buttons
        }
        _binding = FragmentButtonsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set click listeners for all buttons
        binding.apply {
            buttonAdd.setOnClickListener { onButtonClick("+") }
            buttonSubtract.setOnClickListener { onButtonClick("—") }
            buttonMultiply.setOnClickListener { onButtonClick("X") }
            buttonDivide.setOnClickListener { onButtonClick("/") }
            buttonMod.setOnClickListener { onButtonClick("%") }

            button0.setOnClickListener{ onButtonClick("0")}
            button1.setOnClickListener{ onButtonClick("1")}
            button2.setOnClickListener{ onButtonClick("2")}
            button3.setOnClickListener{ onButtonClick("3")}
            button4.setOnClickListener{ onButtonClick("4")}
            button5.setOnClickListener{ onButtonClick("5")}
            button6.setOnClickListener{ onButtonClick("6")}
            button7.setOnClickListener{ onButtonClick("7")}
            button8.setOnClickListener{ onButtonClick("8")}
            button9.setOnClickListener{ onButtonClick("9")}

            buttonDecimal.setOnClickListener{ onButtonClick(".")}
            buttonCe.setOnClickListener{ onButtonClick("CE")}
            buttonClear.setOnClickListener{ onButtonClick("C")}
            buttonEquals.setOnClickListener{ onButtonClick("=")}




        }
    }

    private fun onButtonClick(buttonText: String) {
        calculatorListener.onButtonClicked(buttonText)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is CalculatorListener) {
            calculatorListener = context
        } else {
            throw RuntimeException("$context must implement CalculatorListener")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}