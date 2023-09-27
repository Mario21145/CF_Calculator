package com.example.calculator_cf

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.calculator_cf.databinding.FragmentGetNameBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class getName : Fragment() {

    private val viewModel: AppViewModel by viewModels()
    private lateinit var binding: FragmentGetNameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGetNameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val button = binding.buttonName
        val name = binding.editTextText.text.toList()

        button.setOnClickListener {
            viewModel.check_Consonants_And_Vocals(name)
        }



    }






    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            getName().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}