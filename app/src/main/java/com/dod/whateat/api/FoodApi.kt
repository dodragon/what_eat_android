package com.dod.whateat.api

import com.dod.whateat.data.DefaultData
import retrofit2.Response
import retrofit2.http.GET

interface FoodApi {

    @GET("food/cnt")
    suspend fun getFoodCount(
        //no query
    ): Response<DefaultData>
}