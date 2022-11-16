package com.example.flowers_shop.data

import io.reactivex.Observable
import retrofit2.http.GET

interface Interface  {

    @GET("/flowers")
    fun flower(): Observable<List<Flowers>>
}
