package com.example.labs

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.labs.databinding.ActivityMain2Binding
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.Arrays
import java.util.stream.Collectors.toList

class MainActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main2)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_cities, R.id.navigation_selected, R.id.navigation_saved
            )
        )

        val mRecycleView = binding.recycleView
        mRecycleView.setHasFixedSize(true)
        val mLayoutManager = LinearLayoutManager(this)
        mRecycleView.layoutManager = mLayoutManager
        val citiesList = resources.getStringArray(R.array.cities)
        val mAdapter = MyRecycleViewAdapter(citiesList, this)
        mRecycleView.adapter = mAdapter

        navView.setupWithNavController(navController)
    }
}