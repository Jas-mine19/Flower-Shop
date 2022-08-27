package com.example.flowers_shop.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Api {

    val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("http://10.0.2.2:4000/api/")
        .build()

    fun create(): ApiInterface {

        return retrofit.create(ApiInterface::class.java)

    }
}

