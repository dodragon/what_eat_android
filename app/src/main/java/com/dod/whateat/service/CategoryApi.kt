package com.dod.whateat.service

import com.dod.whateat.data.DefaultData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CategoryApi {

    @GET("get/all/category")
    suspend fun selectAllCategory(): Response<DefaultData>

    @GET("get/type/category")
    suspend fun selectTypeCategory(
        @Query("type")type: Int
    ): Response<DefaultData>
}