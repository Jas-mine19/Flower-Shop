package com.example.flowers_shop.data

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("category/categories")
    fun getCategory(): Response<List<Category>>

    @GET("flower/flowers")
    fun getFlower(): Response<List<Flowers>>

    @GET("advertising/advertisings")
    fun getAdvertising(): Response<List<Advertising>>

    @GET("basket/baskets")
    fun getBasket(): Response<List<Basket>>



}