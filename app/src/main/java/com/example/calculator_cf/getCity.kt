package com.example.calculator_cf

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.example.calculator_cf.data.Dataset
import com.example.calculator_cf.databinding.FragmentGetCityBinding


class getCity : Fragment() {
    private val viewModel: AppViewModel by activityViewModels()
    private lateinit var binding: FragmentGetCityBinding
    private lateinit var selectedCity: String
    private lateinit var result_city: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_get_city, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this
        binding.appViewModel = AppViewModel()


        var data = Dataset()


        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            data.cities
        )
        binding.cities.adapter = adapter
        binding.cities.setSelection(5)
        binding.cities.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

                selectedCity = data.cities[p2]
                result_city = viewModel.calcCity(selectedCity)

                if (result_city == "comune") {
                    Log.d("City", "Comune inizializzato con successo")
                } else {
                    viewModel.setCity(selectedCity)
                }

            }


            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

        }



        binding.buttonCity.setOnClickListener() {
            if (result_city.isEmpty()) {
                viewModel.showToast(requireContext(), "Selezionare il comune", 30)
            } else {
                viewModel.setCity(selectedCity)
            }
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