package com.example.calculator_cf

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.text.set
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.example.calculator_cf.databinding.FragmentGetNameBinding
import org.w3c.dom.Text


class getName : Fragment() {

    private val viewModel: AppViewModel by activityViewModels()
    private lateinit var binding: FragmentGetNameBinding
    private lateinit var result_name: String
    private lateinit var name : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_get_name, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val button_name = binding.buttonName
        name = ""

        binding.LiveCFText.text =  viewModel.live_CF.value

        binding.editTextText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                name = binding.editTextText.text.toString()
                val nameUpperCase = name.map { it.uppercaseChar() }
                val name_list = nameUpperCase.toList()
                result_name = viewModel.calcConsonants(name_list)

                if (name.isNotEmpty()) {
                    binding.LiveCFText.text = getString(R.string.CF_live_Data, viewModel.live_CF.value + result_name)
                } else if(name.isEmpty()){
                    binding.LiveCFText.text = getString(R.string.CF_live_Data, viewModel.live_CF.value)
                }



            }

            override fun afterTextChanged(s: Editable?) {
            }
        })


        button_name.setOnClickListener {

             if(name.isEmpty()){
                 viewModel.showToast(requireContext() , "Il campo nome è vuoto" , 30)
             } else {
                 findNavController().navigate(R.id.action_getName_to_getDate)
                 viewModel.calcCF(result_name)
             }



        }

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            getName().apply {
                arguments = Bundle().apply {

                }
            }
    }
}