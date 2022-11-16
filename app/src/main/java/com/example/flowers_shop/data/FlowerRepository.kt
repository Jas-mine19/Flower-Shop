package com.example.flowers_shop.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

class FlowerRepository(
    private val localDB: LocalDataSource,
    private val networkDB: NetworkDataSource
) : FlowerRepositoryInterface {

    private val repoMediatorLiveData = MediatorLiveData<List<Flowers>>()
    override val flowerRepos: LiveData<List<Flowers>>
        get() = repoMediatorLiveData

    private val networkMediatorLiveData = MediatorLiveData<NetworkState>()
    override val networkState: LiveData<NetworkState>
        get() = networkMediatorLiveData

    private val apiNetworkState = networkDB.networkState
    private val dbNetworkState = localDB.networkState
    private val networkRepo = networkDB.flowerRepo
    private val localRepo = localDB.trendingRepo

    init {
        networkMediatorLiveData.addSource(apiNetworkState) {
            networkMediatorLiveData.postValue(it)
        }
        networkMediatorLiveData.addSource(dbNetworkState) {
            networkMediatorLiveData.postValue(it)
        }
        repoMediatorLiveData.addSource(networkRepo) {
            repoMediatorLiveData.postValue(it)
        }
        repoMediatorLiveData.addSource(localRepo) {
            repoMediatorLiveData.postValue(it)
        }
    }

    override fun getFlowerList() {
            localDB.getLocalData()
    }

    override fun flowerDataFromNet() {
        Log.e("Tag", "getting from network")
        networkDB.flowerRepos()
    }


}
