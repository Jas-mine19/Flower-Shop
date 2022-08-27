package com.example.flowers_shop.mvvm

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flowers_shop.data.Advertising
import com.example.flowers_shop.data.Api
import com.example.flowers_shop.data.Category
import com.example.flowers_shop.data.Flowers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class HomeViewModel : ViewModel() {
    private val apiInterface = Api().create()

    private val _categoryLiveData = MutableLiveData<List<Category>>()
    val categoryLiveData = _categoryLiveData

    private val _errorCategoryLiveData = MutableLiveData<String>()
    val errorCategoryLiveData = _errorCategoryLiveData

    private val _advertisingLiveData = MutableLiveData<List<Advertising>>()
    val advertisingLiveData = _advertisingLiveData

    private val _errorAdvertisingLiveData = MutableLiveData<String>()
    val errorAdvertisingLiveData = _errorAdvertisingLiveData


    private val _flowerLiveData = MutableLiveData<List<Flowers>>()
    val flowerLiveData = _flowerLiveData

    private val _errorFlowerLiveData = MutableLiveData<String>()
    val errorFlowerLiveData = _errorFlowerLiveData

    fun getCategoryList() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = apiInterface.getCategory()
            try {
                if (response.body() != null) {
                    _categoryLiveData.postValue(response.body())
                } else {
                    _errorCategoryLiveData.postValue("Response is null!")
                }

            } catch (e: Exception) {
                _errorCategoryLiveData.postValue("")
            }
        }
    }

    fun getAdvertisingList() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = apiInterface.getAdvertising()
            try {
                if (response.body() != null) {
                    _advertisingLiveData.postValue(response.body())
                } else {
                    _errorAdvertisingLiveData.postValue("Response is null!")
                }

            } catch (e: Exception) {
                _errorAdvertisingLiveData.postValue("")
            }
        }
    }

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