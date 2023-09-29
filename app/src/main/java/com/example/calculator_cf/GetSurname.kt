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
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.calculator_cf.databinding.FragmentGetSurnameBinding

class GetSurname : Fragment() {

    private val ViewModel: AppViewModel by viewModels()
    private lateinit var binding : FragmentGetSurnameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_get_surname, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var button_surname = binding.buttonSurname
        button_surname.setOnClickListener(){ findNavController().navigate(R.id.action_getSurname_to_getName) }



        binding.editTextSurname.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                val surname = binding.editTextSurname.text
                Log.d("cognome", "$surname")
                val surname_list = surname.toList()
                val result = ViewModel.check_Consonants_And_Vocals(surname_list)

                if (ViewModel.checkIsEmpty(surname_list)) {
                    ViewModel.live_CF.value = ViewModel.live_CF.value.plus(result)
                    binding.LiveCFText.text = getString(R.string.CF_live_Data)
                } else {
                    Log.d("result1", "$result")
                    ViewModel.live_CF.value = ViewModel.live_CF.value.plus(result)
                    var r = ViewModel.live_CF.value
                    Log.d("result_surname", "$r")
                    binding.LiveCFText.text = getString(R.string.CF_live_Data, result)
                }


            }

            override fun afterTextChanged(s: Editable?) {
            }
        })











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