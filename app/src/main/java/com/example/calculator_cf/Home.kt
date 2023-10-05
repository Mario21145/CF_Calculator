package com.example.calculator_cf

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.calculator_cf.databinding.FragmentGetNameBinding
import com.example.calculator_cf.databinding.FragmentHomeBinding
import androidx.navigation.fragment.findNavController

class Home : Fragment() {

    private val viewModel: AppViewModel by activityViewModels()
    private lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
        }

        binding.Exit.setOnClickListener {viewModel.exit_app()}
        binding.GoGetName.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_getSurname)
        }

        Log.d("liveCfHome", viewModel.live_CF.value.toString())
    }


    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Home().apply {
                arguments = Bundle().apply {
                }
            }
    }
}