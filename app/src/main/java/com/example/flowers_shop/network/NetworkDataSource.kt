package com.example.flowers_shop.network

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.flowers_shop.common.interfaces.Interface
import com.example.flowers_shop.models.Flowers
import io.reactivex.schedulers.Schedulers

class NetworkDataSource(
    private val apiService: Interface,
    private val localDS: LocalDataSource
) {

    private val _networkState = MutableLiveData<NetworkState>()
    val networkState: LiveData<NetworkState>
        get() = _networkState

    private val _flowerRepos = MutableLiveData<List<Flowers>>()
    val flowerRepo: LiveData<List<Flowers>>
        get() = _flowerRepos

    @SuppressLint("CheckResult")
    fun flowerRepos() {
        try {
            _networkState.postValue(NetworkState.LOADING)
            apiService.flower()
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        _networkState.postValue(NetworkState.LOADED)
                        _flowerRepos.postValue(it)
                        localDS.saveData(it)
                    },
                    {
                        _networkState.postValue(NetworkState.ERROR)
                        Log.e("Tag", it.message.toString())
                    }
                )

        } catch (e: Exception) {
            _networkState.postValue(NetworkState.ERROR)
            Log.e("Tag", e.message.toString())
        }
    }
}