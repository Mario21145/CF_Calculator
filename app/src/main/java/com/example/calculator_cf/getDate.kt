package com.example.calculator_cf

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.calculator_cf.databinding.FragmentGetDateBinding
import com.example.calculator_cf.databinding.FragmentGetSurnameBinding


class getDate : Fragment() {

    private val viewModel: AppViewModel by activityViewModels()
    private lateinit var binding: FragmentGetDateBinding
    private lateinit var result_date : String
    private lateinit var result_day : String
    private lateinit var result_month : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_get_date, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var button_date = binding.dateButton


        //Date
        binding.date.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                val date = binding.date.text.toString()
                result_date = viewModel.calcDate(date)

                viewModel.setDate(date)
                if(result_date == "Error"){

                } else if(viewModel.date.value!!.isEmpty()){
                    viewModel.setDate(date)
                } else if(viewModel.date.value!!.isNotEmpty()){
                    viewModel.setCF(result_date)
                }

                binding.LiveCFText.text = getString(R.string.CF_live_Data, viewModel.live_CF.value)

            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        //Day
        binding.day.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                val day = binding.day.text.toString()
                result_day = viewModel.calcDay(day)


                viewModel.setDate(day)
                if(result_date == "Error"){

                } else if(viewModel.day.value!!.isEmpty()){
                    viewModel.setDay(day)
                    viewModel.setCF(result_day)
                } else if(viewModel.day.value!!.isNotEmpty()){
                    viewModel.setDay(day)
                    viewModel.setCF(result_day)
                }

                binding.LiveCFText.text = getString(R.string.CF_live_Data, viewModel.live_CF.value)

            }

            override fun afterTextChanged(s: Editable?) {
            }
        })



        //Spinner
        val months = listOf(
            "Gennaio", "Febbraio", "Marzo", "Aprile",
            "Maggio", "Giugno", "Luglio", "Agosto",
            "Settembre", "Ottobre", "Novembre", "Dicembre" , "mese"
        )
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item , months)
        binding.month.adapter = adapter
        binding.month.setSelection(12)
        binding.month.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

                var selectedMonth = months[p2]
                val result_month = viewModel.calcMonth(selectedMonth)


                if(result_month == "mese"){

                } else if(viewModel.month.value!!.isEmpty()){
                    viewModel.setMonth(selectedMonth)
                    viewModel.setCF(result_month)
                } else {
                    viewModel.setMonth(selectedMonth)
                    viewModel.setCF(result_month)
                }

                binding.LiveCFText.text = getString(R.string.CF_live_Data, viewModel.live_CF.value)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }



        button_date.setOnClickListener {

            if(result_date == "Error"){
                val msg = getString(R.string.Error_Date)
                viewModel.showToast(requireContext() , "$msg" , 20)
            }

            findNavController().navigate(R.id.action_getDate_to_getSex)

            val name = viewModel.name.value
            val surname = viewModel.surname.value
            val date = viewModel.date.value
            val day = viewModel.day.value
            val month = viewModel.month.value

            Log.d("Variabili" , "nome: $name / cognome: $surname / anno: $date / giorno: $day / mese: $month  " )


        }

    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            getDate().apply {
                arguments = Bundle().apply {

                }
            }
    }

}