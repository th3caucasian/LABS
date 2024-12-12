package com.example.labs

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.labs.databinding.ActivityMain2Binding
import com.example.labs.db.AppDatabase
import com.example.labs.db.AppUserCity
import com.example.labs.db.City
import com.example.labs.db.CityDao
import com.example.labs.db.DatabaseProvider
import com.example.labs.ui.saved.RecycleViewAdapterSaved
import java.util.TreeSet


class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    private lateinit var navController: NavController
    private lateinit var savedCities: MutableList<String>
    var citiesNameList: Array<String>? = null
    var citiesList: List<City>? = null
    var adapterSaved: RecycleViewAdapterSaved? = null
    lateinit var db: AppDatabase
    lateinit var cityDao: CityDao
    var userId: Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        citiesNameList = intent.extras!!.getStringArray("city_names")
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        val navView = binding.navView
        navController = findNavController(R.id.nav_host_fragment_activity_main2)
        navView.setupWithNavController(navController)
        additionalThread2()
        ContextProvider.initialize(this)
        userId = intent.extras!!.getInt("userId")
    }

    fun onTextViewClicked(cityName: String?) {
        val city = Bundle()
        val cityObject = citiesList!!.find { it.name == cityName }
        city.putSerializable("city", cityObject)
        navController.navigate(R.id.navigation_selected, city)
    }

    fun onCityAdded(cityName: String?) {
        val cityId = cityDao.findCityIdByName(cityName)
        if (cityDao.userHasCityAdded(userId, cityId) == null)
            cityDao.insertAppUserCity(AppUserCity(userId, cityId))
    }

    fun onCityDeleted(cityName: String?) {
        val cityId = cityDao.findCityIdByName(cityName)
        if (cityDao.userHasCityAdded(userId, cityId) != null)
            cityDao.deleteAppUserCity(userId, cityId)
        adapterSaved!!.notifyItemRemoved(savedCities.indexOf(cityName))
        adapterSaved!!.removeItem(cityName)
    }

    fun getSavedCities(): MutableList<String> {
        savedCities = cityDao.getAllSavedCities(userId)
        return savedCities
    }

    fun configureAdapterSaved(savedAdapted: RecycleViewAdapterSaved) {
        adapterSaved = savedAdapted
    }

    fun getDb() {
        db = DatabaseProvider.getDatabase(this)
        cityDao = db.cityDao()
        citiesList = cityDao.getAllCities()
    }

    private fun additionalThread2() {
        val thread2 = Thread(Runnable {
            getDb()
        })
        thread2.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        ContextProvider.clearContext()
    }

}