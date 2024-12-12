package com.example.labs.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CityDao {

    @Query("SELECT * FROM City")
    fun getAllCities(): List<City>

    @Insert (onConflict = OnConflictStrategy.IGNORE)
    fun insert(city: City)

    @Query("DELETE FROM City")
    fun delete()

    @Insert (onConflict = OnConflictStrategy.IGNORE)
    fun insertUser(user: AppUser)

    @Query("SELECT * FROM AppUser")
    fun getAllUsers(): List<AppUser>

    @Query("SELECT * FROM AppUser WHERE login = :login AND password = :password")
    fun getUserByLoginAndPassword(login: String, password: String): AppUser?

    @Query("DELETE FROM AppUser")
    fun deleteUsers()

    @Insert (onConflict = OnConflictStrategy.IGNORE)
    fun insertAppUserCity(appUserCity: AppUserCity)

    @Query("DELETE FROM appUserCity WHERE userId = :userId AND cityId = :cityId")
    fun deleteAppUserCity(userId: Int, cityId: Int)

    @Query("SELECT * FROM AppUserCity")
    fun getAllUsersCities(): List<AppUserCity>

    @Query("SELECT * FROM AppUserCity WHERE userId = :userId AND cityId = :cityId")
    fun userHasCityAdded(userId: Int, cityId: Int): AppUserCity

    @Query("SELECT id FROM City WHERE name = :name")
    fun findCityIdByName(name: String?): Int

    @Query("SELECT c.name AS name FROM City c JOIN AppUserCity auc ON c.id = auc.cityId WHERE auc.userId = :userId")
    fun getAllSavedCities(userId: Int): MutableList<String>
}