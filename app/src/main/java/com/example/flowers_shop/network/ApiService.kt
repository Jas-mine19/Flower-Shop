package com.example.flowers_shop.network

import com.example.flowers_shop.models.Advertising
import com.example.flowers_shop.models.Basket
import com.example.flowers_shop.models.Category
import com.example.flowers_shop.models.Favorite
import com.example.flowers_shop.models.Flowers
import com.example.flowers_shop.models.User
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiInterface {
    @GET("categories")
    suspend fun getCategory(): Response<List<Category>>

    @GET("flowers")
    suspend fun getFlower(): Response<List<Flowers>>

    @GET("advertising")
    suspend fun getAdvertising(): Response<List<Advertising>>

    @GET("basket/baskets")
    suspend fun getBasket(): Response<List<Basket>>

    @POST("favorites")
    fun addToFavorite(@Body body: Favorite): Call<ResponseBody>

    @GET("favorite/favorites")
    suspend fun getFavorite(): Response<List<Favorite>>

    @GET("flower/{id}")
    suspend fun getFlowerById(): Response<List<Flowers>>

    @GET ("categorylist/{catid}")
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