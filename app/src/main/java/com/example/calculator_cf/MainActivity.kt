package com.example.calculator_cf

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.findNavController

class MainActivity : AppCompatActivity() {

    private lateinit var navController : NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }




    var binding_navigation_graph = findViewById<View>(R.id.nav_host_fragment)
    val navController = findNavController(binding_navigation_graph)


    nav

}