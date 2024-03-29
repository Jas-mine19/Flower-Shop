package com.example.flowers_shop.ui


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flowers_shop.models.Advertising
import com.example.flowers_shop.models.Api
import com.example.flowers_shop.models.Category
import com.example.flowers_shop.models.Flowers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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

    fun getUpdateCategory(categoryId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = apiInterface.getUpdateFlower(categoryId)
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

//    fun getFlowerById(flowerId: Int) {
//        viewModelScope.launch(Dispatchers.IO) {
//            val response = apiInterface.getFlowerById()
//            try {
//                if (response.body() != null) {
//                    _flowerLiveData.postValue(response.body())
//                } else {
//                    _errorFlowerLiveData.postValue("Response is null!")
//                }
//
//            } catch (e: Exception) {
//                _errorFlowerLiveData.postValue("")
//            }
//        }
//    }


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