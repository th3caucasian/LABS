package com.example.labs

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.labs.databinding.ActivityMain2Binding
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.json.JSONArray
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL


class MainActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        val navView = binding.navView
        navController = findNavController(R.id.nav_host_fragment_activity_main2)
        navView.setupWithNavController(navController)

        threadManager()
    }

    fun onTextViewClicked(city: String) {
        val city_name = Bundle()
        city_name.putString("city", city)
        navController.navigate(R.id.navigation_selected, city_name)
    }

    private fun loadJSON() {
        // JSON
        val url1 = "https://raw.githubusercontent.com/Lpirskaya/JsonLab/master/City2022.json"
        val url: URL? = try {
            URL(url1)
        } catch (e: MalformedURLException) {
            e.printStackTrace()
            null
        }

        // Establish connection
        var conn: HttpURLConnection? = null
        if (url != null) {
            try {
                conn = url.openConnection() as HttpURLConnection
            }
            catch (e: IOException) {
                e.printStackTrace()
            }
        }

        // Checking connection
        if (conn != null) {
            try {
                if (conn.responseCode == HttpURLConnection.HTTP_OK)
                {
                    Log.e("JSON", "${conn.responseCode}")
                }
            }
            catch (e: IOException) {
                e.printStackTrace()
            }

            // Reading JSON

            var response = StringBuilder()
            var input = BufferedReader(InputStreamReader(conn.inputStream), 8192)
            var line: String? = null

            do
            {
                line = input.readLine()
                response.append(line)
            } while(line != null)

            val jsonArray = JSONArray(response.toString())
            for (i in 0..jsonArray.length()-1)
            {
                val arrayItem = jsonArray.getJSONObject(i)
                val population = arrayItem.getString("Population")
                Log.e("JSON", "Population: ${population}")
                val country = arrayItem.getString("Country")
                Log.e("JSON", "Country: ${country}")
                val name = arrayItem.getString("Name")
                Log.e("JSON", "Name: ${name}")
                val language = arrayItem.getString("language")
                Log.e("JSON", "language: ${language}")
                val square = arrayItem.getString("square")
                Log.e("JSON", "square: ${square}")
            }
        }


    }


    private fun threadManager() {
        val thread = Thread(Runnable {
            loadJSON()
        })

        thread.start()
    }

}