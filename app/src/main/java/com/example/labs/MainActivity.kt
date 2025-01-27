package com.example.labs

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.labs.databinding.ActivityMainBinding
import com.example.labs.db.AppDatabase
import com.example.labs.db.AppUser
import com.example.labs.db.AppUserCity
import com.example.labs.db.City
import com.example.labs.db.CityDao
import com.example.labs.db.DatabaseProvider
import com.google.gson.Gson
import org.json.JSONArray
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL



class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var emailfld: EditText
    private lateinit var passfld: EditText
    private lateinit var btn: Button
    var citiesNameList = arrayOf<String>()
    var citiesList = arrayOf<City>()
    lateinit var db: AppDatabase
    lateinit var cityDao: CityDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        emailfld = binding.editTextTextEmailAddress
        passfld = binding.editTextTextPassword
        btn = binding.button
        setContentView(binding.root)
        additionalThread()
    }

    fun btnClick(view: View?) {
        val logins = resources.getStringArray(R.array.data)
        val inputUser = AppUser()
        inputUser.login = emailfld.text.toString()
        inputUser.password = passfld.text.toString()
        val currentUser = cityDao.getUserByLoginAndPassword(inputUser.login, inputUser.password)
        if (currentUser != null)
        {
            val intent = Intent(this, MainActivity2::class.java)
            intent.putExtra("city_names", citiesNameList)
            intent.putExtra("cities", citiesList)
            intent.putExtra("userId", currentUser.id)
            startActivity(intent)
        }
        else
            btn.setBackgroundColor(Color.RED)
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
            val response = StringBuilder()
            val input = BufferedReader(InputStreamReader(conn.inputStream), 8192)
            var line: String? = null

            do
            {
                line = input.readLine()
                response.append(line)
            } while(line != null)

            val jsonArray = JSONArray(response.toString())
            val gson = Gson()
            for (i in 0..jsonArray.length()-1)
            {
                val arrayItem = jsonArray.getJSONObject(i)
                val cityItem = gson.fromJson(arrayItem.toString(), City::class.java)
                cityDao.insert(cityItem)
                citiesNameList += cityItem.name
                citiesList += cityItem
                Log.e("CITY", "name: ${cityItem.name}, country: ${cityItem.country}, population: ${cityItem.population}, language: ${cityItem.language}, square: ${cityItem.square}")
            }
            citiesNameList = citiesNameList.sorted().toTypedArray()
        }

    }

    private fun getDb()
    {
        db = DatabaseProvider.getDatabase(this)
        cityDao = db.cityDao()
    }

    private fun additionalThread() {
        val thread = Thread(Runnable {
            getDb()
            loadJSON()
        })
        thread.start()
    }
}
