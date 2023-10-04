package com.example.calculator_cf

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.calculator_cf.databinding.FragmentGetDateBinding
import com.example.calculator_cf.databinding.FragmentGetSexBinding


class getSex : Fragment() {
    private val viewModel: AppViewModel by activityViewModels()
    private lateinit var binding: FragmentGetSexBinding
    private lateinit var men_radio: RadioButton
    private lateinit var women_radio: RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_get_sex, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        men_radio = binding.men
        women_radio = binding.women

        var radioGroup = binding.radioGroup

        var text_men_radio = binding.men.text.toString()
        var text_women_radio = binding.women.text.toString()

        binding.radioGroup.setOnClickListener(){

        }

        binding.buttonSex.setOnClickListener() {

            val radioGroup = binding.radioGroup
            val numButtons = radioGroup.childCount
            var isActive = false

            for (i in 0 until numButtons) {
                val radioButton = radioGroup.getChildAt(i) as RadioButton

                radioButton.isSelected

                if (radioButton.isChecked) {
                    isActive = true
                    break
                }
            }

            if (isActive) {
                findNavController().navigate(R.id.action_getSex_to_getCity)
            } else {
                viewModel.showToast(requireContext() , "Selezionare il sesso" , 30)
            }



        }


    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            getSex().apply {
                arguments = Bundle().apply {

                }
            }
    }
}