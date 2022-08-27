package com.example.flowers_shop.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flowers_shop.data.Api
import com.example.flowers_shop.data.Basket
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class BasketViewModel : ViewModel() {
    private val apiInterface = Api().create()

    private val _basketLiveData = MutableLiveData<List<Basket>>()
    val basketLiveData = _basketLiveData

    private val _errorBasketLiveData = MutableLiveData<String>()
    val errorBasketLiveData = _errorBasketLiveData

    fun getBasketList() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = apiInterface.getBasket()
            try {
                if (response.body() != null) {
                    _basketLiveData.postValue(response.body())
                } else {
                    _errorBasketLiveData.postValue("Response is null!")
                }

            } catch (e: Exception) {
                _errorBasketLiveData.postValue("")
            }
        }
    }

}