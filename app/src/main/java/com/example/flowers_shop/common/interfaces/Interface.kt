package com.example.flowers_shop.common.interfaces

import com.example.flowers_shop.models.Flowers
import io.reactivex.Observable
import retrofit2.http.GET

interface Interface  {

    @GET("/flowers")
    fun flower(): Observable<List<Flowers>>
}
