package com.example.calculator_cf

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.calculator_cf.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var binding_navigation_graph = findViewById<View>(R.id.nav_host_fragment)
        val navController = binding_navigation_graph.findNavController()

    }







}