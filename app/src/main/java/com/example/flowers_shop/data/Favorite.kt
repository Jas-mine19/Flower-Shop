package com.example.flowers_shop.data

data class Favorite(
    val favoriteId:  Int,
    val flowerName: String,
    val flowerImage: Int,
    val price: Long,
    val isSelected: Boolean
)
