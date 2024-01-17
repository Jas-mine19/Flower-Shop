package com.example.flowers_shop.models

import com.example.flowers_shop.network.ApiInterface
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Api {
    private val interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }


    private val retrofit = Retrofit
        .Builder()
        .client(OkHttpClient.Builder().addInterceptor(interceptor).build())
        .baseUrl("https://6476e5b89233e82dd53a798d.mockapi.io/api/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun create(): ApiInterface {

        return retrofit.create(ApiInterface::class.java)

    }
}

