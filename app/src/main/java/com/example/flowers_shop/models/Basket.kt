package com.example.flowers_shop.models

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class Basket(
    @PrimaryKey(autoGenerate = true)
    val flowerName: String? = null,
    val flowerPrice: Double? = null,
    val amount: Int? = null,
    val totalPrice: Double? = null
)
