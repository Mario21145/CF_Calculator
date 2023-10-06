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
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.example.calculator_cf.databinding.FragmentGetSurnameBinding
import com.example.calculator_cf.AppViewModel

class GetSurname : Fragment() {

    private val viewmodelapp: AppViewModel by activityViewModels()
    private var binding: FragmentGetSurnameBinding? = null
    private lateinit var surname : String
    private lateinit var result_surname: String



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentGetSurnameBinding.inflate(inflater , container , false)
        binding = fragmentBinding
        return fragmentBinding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply{
            lifecycleOwner = viewLifecycleOwner
           // viewmodel = appViewModel
        }




        var button_surname = binding!!.buttonSurname
        surname = ""
        result_surname = ""

        binding!!.editTextSurname.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                surname = binding!!.editTextSurname.text.toString()
                val surnameUpperCase = surname.map { it.uppercaseChar() }
                val surname_list = surnameUpperCase.toList()
                result_surname = viewmodelapp.calcConsonants(surname_list)
                viewmodelapp.setSurname(surname)
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })

        button_surname.setOnClickListener() {
            if(surname.isEmpty()){
                viewmodelapp.showToast(requireContext(), "Il campo cognome è vuoto" , 30 )
            } else {
                findNavController().navigate(R.id.action_getSurname_to_getName)
                viewmodelapp.calcCF(result_surname)
            }
        }

        Log.d("liveCfSurname", viewmodelapp.live_CF.value.toString())
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