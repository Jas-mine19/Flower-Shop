package com.example.flowers_shop.ui.favorite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.flowers_shop.models.Api
import com.example.flowers_shop.models.Favorite

class FavoriteViewModel : ViewModel() {
    private val apiInterface = Api().create()

    private val _favoriteLiveData = MutableLiveData<List<Favorite>>()
    val basketLiveData = _favoriteLiveData

    private val _errorFavoriteLiveData = MutableLiveData<String>()
    val errorFavoriteLiveData = _errorFavoriteLiveData}

//    fun addToFavorite(
//        favoriteId: Int,
//        flowerName: String,
//        flowerPrice: Double,
//        amount: Int,
//        totalPrice: Double
//    ) {
//        viewModelScope.launch(Dispatchers.IO) {
//            apiInterface.addToFavorite(
//                Favorite(
//                    favoriteId,
//                    flowerName,
//                    flowerPrice,
//                    amount,
//                    totalPrice
//                )
//            ).enqueue(object :
//                RetrofitCallback<ResponseBody>(mContext, true) {
//                override fun onResponse(
//                    call: Call<ResponseBody>,
//                    response: Response<ResponseBody>
//                ) {
//                    if (response.isSuccessful) {
//
//                    } else {
//                    }
//                }
//            })
//        }
//
//        fun getBasketList() {
//            viewModelScope.launch(Dispatchers.IO) {
//                val response = apiInterface.getFavorite()
//                try {
//                    if (response.body() != null) {
//                        _favoriteLiveData.postValue(response.body())
//                    } else {
//                        _errorFavoriteLiveData.postValue("Response is null!")
//                    }
//
//                } catch (e: Exception) {
//                    _errorFavoriteLiveData.postValue("")
//                }
//            }
//        }
//
//    }}

