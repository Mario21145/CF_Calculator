package com.example.calculator_cf

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.example.calculator_cf.databinding.FragmentGetCityBinding
import com.example.calculator_cf.databinding.FragmentRecapBinding


class Recap : Fragment() {

    private val viewModel: AppViewModel by activityViewModels()
    private lateinit var binding: FragmentRecapBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_recap, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        binding.appViewModel = AppViewModel()

        binding.LiveCFText.text = getString(R.string.CF_live_Data, viewModel.live_CF.value)

//        binding.LiveCFText.text = getString(R.string.nameRecap, viewModel.live_CF.value + viewModel.name.value)
//        binding.LiveCFText.text = getString(R.string.surnameRecap, viewModel.live_CF.value + viewModel.surname.value)
//        binding.LiveCFText.text = getString(R.string.cityRecap, viewModel.live_CF.value + viewModel.city.value)
//        binding.LiveCFText.text = getString(R.string.dateRecap, viewModel.live_CF.value + viewModel.date.value)

    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Recap().apply {
                arguments = Bundle().apply {

                }
            }
    }
}