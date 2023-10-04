package com.example.calculator_cf

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.calculator_cf.databinding.FragmentGetSurnameBinding

class GetSurname : Fragment() {

    private val viewModel: AppViewModel by activityViewModels()
    private lateinit var binding: FragmentGetSurnameBinding
    private lateinit var surname : String
    private lateinit var result_surname: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_get_surname, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var button_surname = binding.buttonSurname
        surname = ""



        Log.d("liveCfSurname", viewModel.live_CF.value.toString())
        binding.editTextSurname.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                binding.LiveCFText.text = getString(R.string.CF_live_Data , viewModel.live_CF.value)
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                surname = binding.editTextSurname.text.toString()
                val surnameUpperCase = surname.map { it.uppercaseChar() }
                val surname_list = surnameUpperCase.toList()
                result_surname = viewModel.calcConsonants(surname_list)

                if (surname.isNotEmpty()) {
                    binding.LiveCFText.text = getString(R.string.CF_live_Data,viewModel.live_CF.value + result_surname)
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        button_surname.setOnClickListener() {
            if(surname.isEmpty()){
                viewModel.showToast(requireContext(), "Il campo cognome è vuoto" , 30 )
            } else {
                findNavController().navigate(R.id.action_getSurname_to_getName)
                viewModel.calcCF(result_surname)
            }
        }
    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            GetSurname().apply {
                arguments = Bundle().apply {

                }
            }
    }
}