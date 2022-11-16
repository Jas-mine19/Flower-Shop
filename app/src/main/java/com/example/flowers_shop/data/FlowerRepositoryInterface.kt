package com.example.flowers_shop.data

import androidx.lifecycle.LiveData

interface FlowerRepositoryInterface{
    fun getFlowerList ()
    fun flowerDataFromNet ()
    val flowerRepos: LiveData<List<Flowers>>
    val networkState: LiveData<NetworkState>
}