package com.example.flowers_shop.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.flowers_shop.models.Flowers

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
