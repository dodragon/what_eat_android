package com.dod.whateat.api

import com.dod.whateat.data.DefaultData
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface FoodApi {

    @GET("food/cnt")
    suspend fun getFoodCount(
        //no query
    ): Response<DefaultData>

    @FormUrlEncoded
    @POST("food/random")
    suspend fun getRandomFood(
        @Field("page")page: Int,
        @Field("size")size: Int
    )

    @FormUrlEncoded
    @POST("food/selected")
    suspend fun setSelectedFood(
        @Field("seq")page: Long
    )
}