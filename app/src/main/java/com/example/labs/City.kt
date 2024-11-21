package com.example.labs

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity
class City: Serializable {
    @PrimaryKey (autoGenerate = true)
    var id = 0
    @SerializedName("Name")
    @Expose
    lateinit var name: String
    @SerializedName("Country")
    @Expose
    lateinit var country: String
    @SerializedName("Population")
    @Expose
    var population = 0
    @SerializedName("language")
    @Expose
    lateinit var language: String
    @SerializedName("square")
    @Expose
    var square = 0
}