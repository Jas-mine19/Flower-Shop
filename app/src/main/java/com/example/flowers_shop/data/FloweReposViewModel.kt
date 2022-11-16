package com.example.flowers_shop.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel

class FlowerReposViewModel(
private val repository: FlowerRepository
) : ViewModel() {

    private val flowerReposLiveData = MediatorLiveData<List<Flowers>>()
    val flower: LiveData<List<Flowers>>
    get() = flowerReposLiveData

    init {
        flowerReposLiveData.addSource(repository.flowerRepos){
            flowerReposLiveData.value = it
        }
    }

    fun getFlower() {
        repository.getFlowerList()
    }

    val networkState: LiveData<NetworkState> by lazy {
        repository.networkState
    }

    fun flowerDataFromNetwork() {
        repository.flowerDataFromNet()
    }
}