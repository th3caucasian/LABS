package com.example.labs.db

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["login"], unique = true, name = "ui_AppUser_login")])
class AppUser {
    @PrimaryKey (autoGenerate = true)
    var id = 0

    lateinit var login: String

    lateinit var password: String
}