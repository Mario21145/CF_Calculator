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
import com.example.calculator_cf.data.Dataset
import com.example.calculator_cf.databinding.FragmentGetDateBinding


class getDate : Fragment() {

    private val viewModel: AppViewModel by activityViewModels()

    private lateinit var binding: FragmentGetDateBinding
    private lateinit var Date: String
    private lateinit var day: String

    private lateinit var selectedMonth: String
    private lateinit var result_date: String
    private lateinit var result_month: String
    private lateinit var result_day: String

    private lateinit var initialDate :  String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_get_date, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        result_date = ""
        result_month = ""
        result_day = ""

        selectedMonth = ""
        day = ""


        val data = Dataset()
        val button_date = binding.dateButton

        binding.lifecycleOwner = this
        binding.appViewModel = AppViewModel()

        binding.LiveCFText.text = getString(R.string.CF_live_Data, viewModel.live_CF.value)




        //Date
        Date = ""

        initialDate = binding.date.text.toString()



        binding.date.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                Date = binding.date.text.toString()
                result_date = viewModel.calcDate(Date)

                if(Date.isEmpty()){
                    binding.LiveCFText.text =
                        getString(R.string.CF_live_Data, viewModel.live_CF.value)
                } else {
                    viewModel.setDate(Date)
                    binding.LiveCFText.text =
                        getString(R.string.CF_live_Data, viewModel.live_CF.value + result_date)
                }
            }

            override fun afterTextChanged(s: Editable?) {

                if(Date.length == 4){
                    if(Date.isNotEmpty()){
                        if(viewModel.date.value == Date){

                        }
                    }
                }

            }
        })










        binding.day.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                day = binding.day.text.toString()
                binding.LiveCFText.text =
                    getString(R.string.CF_live_Data, viewModel.live_CF.value + day)
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })











        //Spinner
        val adapter =
            ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_dropdown_item,
                data.months
            )
        binding.month.adapter = adapter
        binding.month.setSelection(12)
        binding.month.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

                selectedMonth = data.months[p2]
                result_month = viewModel.calcMonth(selectedMonth)

                if (result_month != "mese") {
                    binding.LiveCFText.text = getString(R.string.CF_live_Data, viewModel.live_CF.value + result_month)
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }

        if(selectedMonth.isNotEmpty()){
            viewModel.setCF(result_month)
        }





        button_date.setOnClickListener {

            if (day.isEmpty() || Date.isEmpty() || selectedMonth.isEmpty()) {
                viewModel.showToast(requireContext(), "Riempire i campi", 30)
            } else {

                viewModel.setDate(Date)
                viewModel.setDay(day.toInt())
                viewModel.setMonth(selectedMonth)
                findNavController().navigate(R.id.action_getDate_to_getSex)
                Log.d("liveCfDate", "${viewModel.live_CF.value}")
            }

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