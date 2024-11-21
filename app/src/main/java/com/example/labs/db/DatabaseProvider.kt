package com.example.labs.db

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

object DatabaseProvider {
    private var database: AppDatabase? = null

    val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("DROP INDEX IF EXISTS ui_city_name")
            database.execSQL("CREATE UNIQUE INDEX ui_City_name ON City(name)")
        }

    }
    fun getDatabase(context: Context): AppDatabase {
        return database ?: synchronized(this) {
            val instance = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "room_mydb")
                .addMigrations(MIGRATION_1_2)
                .build()
            database = instance
            instance
        }
    }
}