package com.example.calculator_cf

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
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

        val radioGroup = binding.radioGroup
        val numberButtons = radioGroup.childCount

        binding.lifecycleOwner = this
        binding.appViewModel = AppViewModel()

        binding.buttonSex.setOnClickListener() {

            var isActive = false
            for (i in 0 until numberButtons) {
                val radioButton = radioGroup.getChildAt(i) as RadioButton

                if (radioButton.isChecked) {
                    isActive = true
                    viewModel.setSex(radioButton.text.toString())
                    viewModel.calcSex(radioButton.text.toString())
                    break
                }
            }
            if (isActive) {

                Log.d("test", "${viewModel.sex.value}")
                if(viewModel.sex.value!!.isNotEmpty()){
                    viewModel.calcSex(viewModel.sex.value.toString())
                    Log.d("liveCF" , "${viewModel.live_CF.value}")
                }

                findNavController().navigate(R.id.action_getSex_to_getCity)
            } else {
                viewModel.showToast(requireContext(), "Selezionare il sesso", 30)
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