package com.example.calculator_cf

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.calculator_cf.databinding.FragmentGetSexBinding


class getCity : Fragment() {
    private val viewModel: AppViewModel by activityViewModels()
    private lateinit var binding: FragmentGetSexBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_get_city, container, false)
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