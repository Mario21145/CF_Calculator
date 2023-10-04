package com.example.calculator_cf

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.example.calculator_cf.databinding.FragmentGetDateBinding
import com.example.calculator_cf.databinding.FragmentGetSexBinding


class getSex : Fragment() {
    private val viewModel: AppViewModel by activityViewModels()
    private lateinit var binding: FragmentGetSexBinding

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

        var men_radio = binding.men
        val text_men_radio = binding.men.text.toString()
        var women_radio = binding.women
        val text_women_radio = binding.women.text.toString()

        men_radio.setOnClickListener(){
            viewModel.setSex(text_men_radio)
            Log.d("sex" , "Il sesso selezionato è: ${viewModel.sex.value}o")
            viewModel.setCF(text_men_radio)
        }

        women_radio.setOnClickListener(){

            viewModel.setSex(text_women_radio)
            Log.d("sex" , "Il sesso selezionato è: ${viewModel.sex.value}")
            viewModel.setCF(text_women_radio)
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