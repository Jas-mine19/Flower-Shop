package com.example.flowers_shop.data

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class LocalDataSource(
    private val flowerDao: FlowerDao
) {

    private val _flowerRepos = MutableLiveData<List<Flowers>>()
    val trendingRepo: LiveData<List<Flowers>>
        get() = _flowerRepos

    private val _networkState = MutableLiveData<NetworkState>()
    val networkState: LiveData<NetworkState>
        get() = _networkState

    fun saveData(list: List<Flowers>?) {
        list?.mapIndexed { index, response ->
            response.flowerId = index
        }
        flowerDao.deleteAll()
        flowerDao.insertAll(list!!)
        Log.e("Tag", "data inserted Success")
    }

    @SuppressLint("CheckResult")
    fun getLocalData() {
        Observable.just(flowerDao)
            .subscribeOn(Schedulers.io())
            .delay(1, TimeUnit.SECONDS)
            .subscribe {
                _networkState.postValue(NetworkState.LOADING)
                val list = it.readData()
                _networkState.postValue(NetworkState.LOADED)
                _flowerRepos.postValue(list)
            }
    }

    fun getFirstItem(): Flowers {
        return flowerDao.findById(0)
    }
}