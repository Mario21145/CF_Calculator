package com.example.calculator_cf

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.calculator_cf.databinding.FragmentGetSurnameBinding

class GetSurname : Fragment() {

    private val viewModel: AppViewModel by activityViewModels()
    private lateinit var binding: FragmentGetSurnameBinding
    private lateinit var result: String
    private lateinit var surname: String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_get_surname, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.appViewModel = AppViewModel()

        surname = ""
        val button_surname = binding.buttonSurname

        binding.LiveCFText.text = getString(R.string.CF_live_Data, viewModel.live_CF.value)

        binding.editTextSurname.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                surname = binding.editTextSurname.text.toString()
                val surnameLowerCase = surname.map { it.uppercaseChar() }
                val surname_list = surnameLowerCase.toList()
                result = viewModel.calcConsonants(surname_list)

                if (binding.editTextSurname.text.isNotEmpty()) {
                   binding.LiveCFText.text = getString(R.string.CF_live_Data, result)
               }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        button_surname.setOnClickListener() {
            if(surname.isEmpty()){
                viewModel.showToast(requireContext(), "Il campo cognome è vuoto" , 30 )
            } else {
                Log.d("liveCfSurname", viewModel.live_CF.value.toString())
                viewModel.setCF(result)
                viewModel.setSurname(surname)
                findNavController().navigate(R.id.action_getSurname_to_getName)
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