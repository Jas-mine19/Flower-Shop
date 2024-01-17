package com.example.flowers_shop.network

import androidx.lifecycle.LiveData
import com.example.flowers_shop.models.Flowers
import com.example.flowers_shop.network.NetworkState

interface FlowerRepositoryInterface{
    fun getFlowerList ()
    fun flowerDataFromNet ()
    val flowerRepos: LiveData<List<Flowers>>
    val networkState: LiveData<NetworkState>
}