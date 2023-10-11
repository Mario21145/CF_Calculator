package com.example.calculator_cf

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
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

        binding.lifecycleOwner = viewLifecycleOwner
        binding.appViewModel = AppViewModel()

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                viewModel.updateCF(11..15)
                viewModel.setCity("")
                findNavController().popBackStack()
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(callback)



        val buttonRecap = binding.buttonSurname

        binding.liveCFText.text = viewModel.live_CF.value

        binding.recapSurname.text = getString(R.string.surnameRecap, viewModel.surname.value)
        binding.recapName.text = getString(R.string.nameRecap, viewModel.name.value)
        binding.recapDate.text = getString(R.string.dateRecap, viewModel.date.value)
        binding.recapMonth.text = getString(R.string.monthRecap, viewModel.month.value)

        if(viewModel.day.value!! > 31){
            viewModel.setDay(viewModel.day.value!!.minus(40))
            binding.recapDay.text = getString(R.string.dayRecap, viewModel.day.value.toString())
        } else {
            binding.recapDay.text = getString(R.string.dayRecap, viewModel.day.value.toString())
        }
        binding.recapSex.text = getString(R.string.sexRecap , viewModel.sex.value)
        binding.recapCity.text = getString(R.string.cityRecap, viewModel.city.value)

        buttonRecap.setOnClickListener(){
            viewModel.reset()
            findNavController().navigate(R.id.action_recap_to_home)
        }

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