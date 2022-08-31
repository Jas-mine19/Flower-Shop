package com.example.flowers_shop.data

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiInterface {
    @GET("category/categories")
    suspend fun getCategory(): Response<List<Category>>

    @GET("flower/flowers")
    suspend fun getFlower(): Response<List<Flowers>>

    @GET("advertising/advertisings")
    suspend fun getAdvertising(): Response<List<Advertising>>

    @GET("basket/baskets")
    suspend fun getBasket(): Response<List<Basket>>

    @GET("favorite/favorites")
    suspend fun getFavorite(): Response<List<Favorite>>

    @GET("flower/flower/{id}")
    suspend fun getFlowerById(): Response<List<Flowers>>

    @GET ("flower/categorylist/{catid}")
    suspend fun getUpdateFlower(@Path("catid") categoryId: Int) : Response<List<Flowers>>

    @POST("user/new")
    fun registerUser(
        @Body info: User
    ) : Call<String>

    @POST("basket/create")
    fun createBasket(
        @Body info: Basket
    ) : Call<String>

    @POST("user/login")
    fun login(
        @Body info: User
    ) : Call<Boolean>

}