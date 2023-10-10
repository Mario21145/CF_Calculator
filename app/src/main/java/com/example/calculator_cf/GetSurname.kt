package com.example.calculator_cf

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
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
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_get_surname, container, false)
        binding.editTextSurname.requestFocus()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.appViewModel = viewModel

        Log.d("View" , "${view}")

        binding.editTextSurname.requestFocus()

        view.setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_DOWN) {
                Log.d("D" , "Action Executed")
                viewModel.updateCF(0..2)
                viewModel.setName("")
                binding.editTextSurname.text.clear()
                return@setOnKeyListener true
            }
            false
        }


        surname = ""
        val button_surname = binding.buttonSurname
        val buttonHome = binding.returnHome

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

//        buttonHome.setOnClickListener(){
//            viewModel.reset()
//            viewModel.showToast(requireContext() , "Variabili resettate" , 30)
//            findNavController().navigate(R.id.action_getSurname_to_home)
//        }
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