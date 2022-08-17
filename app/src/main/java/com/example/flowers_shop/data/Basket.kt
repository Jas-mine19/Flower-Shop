package com.example.flowers_shop.data

data class Basket(
    val basketId: Int,
    val flowerName: String,
    val price: Long,
    val flowerImage:Int,
    val isSelected: Boolean
)
