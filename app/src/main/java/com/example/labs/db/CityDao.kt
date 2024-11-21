package com.example.labs.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CityDao {

    @Query("SELECT * FROM City")
    fun getAll(): List<City>

    @Insert (onConflict = OnConflictStrategy.IGNORE)
    fun insert(city: City)

    @Query("DELETE FROM City")
    fun delete()

}