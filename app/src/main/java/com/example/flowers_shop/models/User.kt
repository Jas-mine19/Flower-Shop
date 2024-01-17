package com.example.flowers_shop.models

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class User(
    @PrimaryKey( autoGenerate = true)
    @SerializedName("usertid")
    val userId: Int,
    val username: String,
    val login: String,
    val password: String
)
