package com.example.labs

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CityDao {

    @Query("SELECT * FROM City")
    fun getAll(): List<City>

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    fun insert(city: City)

    @Query("DELETE FROM City")
    fun delete()

}