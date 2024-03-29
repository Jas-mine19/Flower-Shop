package com.example.flowers_shop.ui


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flowers_shop.models.Api
import com.example.flowers_shop.models.Flowers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class SearchingViewModel() : ViewModel() {
    private val apiInterface = Api().create()

    private val _flowerLiveData = MutableLiveData<List<Flowers>>()
    val flowerLiveData = _flowerLiveData

    private val _errorFlowerLiveData = MutableLiveData<String>()
    val errorFlowerLiveData = _errorFlowerLiveData


    fun getFlowerList() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = apiInterface.getFlower()
            try {
                if (response.body() != null) {
                    _flowerLiveData.postValue(response.body())
                } else {
                    _errorFlowerLiveData.postValue("Response is null!")
                }

            } catch (e: Exception) {
                _errorFlowerLiveData.postValue("")
            }
        }
    }




}
