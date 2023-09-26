package com.example.calculator_cf

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.calculator_cf.databinding.ActivityMainBinding
import com.example.calculator_cf.databinding.FragmentHomeBinding

class MainActivity : AppCompatActivity() {


    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        val navController = navHostFragment?.findNavController()
        //Log.d("navcontroller" , "$navController")
    }







}
