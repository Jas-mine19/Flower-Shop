package com.example.flowers_shop.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flowers_shop.data.Api
import com.example.flowers_shop.data.Favorite
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class FavoriteViewModel : ViewModel() {
    private val apiInterface = Api().create()

    private val _favoriteLiveData = MutableLiveData<List<Favorite>>()
    val basketLiveData = _favoriteLiveData

    private val _errorFavoriteLiveData = MutableLiveData<String>()
    val errorFavoriteLiveData = _errorFavoriteLiveData

    fun getBasketList() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = apiInterface.getFavorite()
            try {
                if (response.body() != null) {
                    _favoriteLiveData.postValue(response.body())
                } else {
                    _errorFavoriteLiveData.postValue("Response is null!")
                }

            } catch (e: Exception) {
                _errorFavoriteLiveData.postValue("")
            }
        }
    }

}

