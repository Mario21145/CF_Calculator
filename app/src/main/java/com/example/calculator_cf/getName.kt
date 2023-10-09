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
import androidx.navigation.fragment.findNavController
import com.example.calculator_cf.databinding.FragmentGetNameBinding


class getName : Fragment() {

    private val viewModel: AppViewModel by activityViewModels()
    private lateinit var binding: FragmentGetNameBinding
    private lateinit var resultName: String
    private lateinit var name: String
    private lateinit var name_list: List<Char>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_get_name, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.appViewModel = AppViewModel()

        name = ""

        val button_name = binding.buttonName

        binding.LiveCFText.text = getString(R.string.CF_live_Data, viewModel.live_CF.value)

        binding.editTextText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                name = binding.editTextText.text.toString()
                val nameUpperCase = name.map { it.uppercaseChar() }
                name_list = nameUpperCase.toList()
                resultName = viewModel.calcConsonants(name_list)
                binding.LiveCFText.text =
                    getString(R.string.CF_live_Data, viewModel.live_CF.value + resultName)
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })


        button_name.setOnClickListener {

            if (name.isEmpty()) {
                viewModel.showToast(requireContext(), "Il campo nome è vuoto", 30)
            } else {
                viewModel.setName(binding.editTextText.text.toString())
                viewModel.setCF(resultName)
                Log.d("liveCfGetName", "${viewModel.live_CF.value}")
                findNavController().navigate(R.id.action_getName_to_getDate)
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