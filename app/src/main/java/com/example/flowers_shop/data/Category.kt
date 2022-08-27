package com.example.flowers_shop.data

data class Category(
    val categoryId:Int,
    val categoryName:String,
    val imageId:Int,
    var isSelected:Boolean
)