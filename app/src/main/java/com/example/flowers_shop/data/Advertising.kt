package com.example.flowers_shop.data

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class Advertising(
    @PrimaryKey(autoGenerate = true)
    val cardId: Int,
    @SerializedName("cardimage")
    val cardimage: String?,
    @SerializedName("discount")
    val discount: String,
    @SerializedName("information")
    val information: String,
    val isSelected: Boolean
)