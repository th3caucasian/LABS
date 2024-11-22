package com.example.labs.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [City::class, AppUser::class, AppUserCity::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cityDao(): CityDao
}