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
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.calculator_cf.databinding.FragmentGetNameBinding
import org.w3c.dom.Text


class getName : Fragment() {

    private val viewModel: AppViewModel by viewModels()
    private lateinit var binding: FragmentGetNameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_get_name, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val button = binding.buttonName

        button.setOnClickListener {
        }

        binding.editTextText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                val name = binding.editTextText.text
                Log.d("nome", "$name")
                val name_list = name.toList()
                val result = viewModel.check_Consonants_And_Vocals(name_list)

                if (viewModel.checkIsEmpty(name_list)) {

                } else {
                    viewModel.live_CF.value = viewModel.live_CF.value.plus(result)
                    var ac = viewModel.live_CF.value
                    Log.d("result", "$ac")
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
            getName().apply {
                arguments = Bundle().apply {

                }
            }
    }
}