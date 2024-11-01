package com.example.labs

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.labs.databinding.ActivityMain2Binding


class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    private lateinit var navController: NavController
    var citiesNameList: Array<String>? = null
    var citiesList: Array<City>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        citiesNameList = intent.extras!!.getStringArray("city_names")
        citiesList = intent.extras!!.getSerializable("cities") as Array<City>?
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        val navView = binding.navView
        navController = findNavController(R.id.nav_host_fragment_activity_main2)
        navView.setupWithNavController(navController)

    }

    fun onTextViewClicked(cityName: String?) {
        val city = Bundle()
        val cityObject = citiesList!!.find { it.name == cityName }
        city.putSerializable("city", cityObject)
        navController.navigate(R.id.navigation_selected, city)
    }



}