package com.example.calculator_cf

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.calculator_cf.data.Dataset
import com.example.calculator_cf.databinding.FragmentGetCityBinding


class getCity : Fragment() {
    private val viewModel: AppViewModel by activityViewModels()
    private lateinit var binding: FragmentGetCityBinding
    private lateinit var selectedCity: String
    private lateinit var resultCity: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_get_city, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.appViewModel = AppViewModel()


        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Log.d("OnBackPressed", "Back key pressed in Fragment.")
                viewModel.updateCF(9..10)

                findNavController().popBackStack()
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(callback)


        val buttonCity = binding.buttonCity
        val returnSex = binding.ReturnSex

        binding.LiveCFText.text =  viewModel.live_CF.value

        val data = Dataset()

        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            data.cities
        )

        binding.cities.adapter = adapter
        binding.cities.setSelection(6)
        binding.cities.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

                selectedCity = data.cities[p2]
                viewModel.setCity(selectedCity)
                resultCity = viewModel.calcCity(selectedCity)

                if (resultCity != "comune") {
                    binding.LiveCFText.text = viewModel.live_CF.value + resultCity
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

        }

        buttonCity.setOnClickListener() {
            if (resultCity.isEmpty()) {
                viewModel.showToast(requireContext(), "Selezionare il comune", 30)
            } else {
                viewModel.setCF(resultCity)
                val resultLastLetter = viewModel.calcLastLetter(viewModel.live_CF.value!!)
                viewModel.setCF(resultLastLetter)
                Log.d("liveCfCity" , "${viewModel.live_CF.value}")
                findNavController().navigate(R.id.action_getCity_to_recap)
                }

        }

        returnSex.setOnClickListener(){
            viewModel.updateCF(9..10)
            viewModel.setSex("")
        }



    }



    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            getCity().apply {
                arguments = Bundle().apply {

                }
            }
    }
}