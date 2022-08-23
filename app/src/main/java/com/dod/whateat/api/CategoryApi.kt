package com.dod.whateat.api

import com.dod.whateat.data.DefaultData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CategoryApi {

    @GET("category/get/all/")
    suspend fun selectAllCategory(
        @Query("page")page: Int,
        @Query("size")size: Int = 20
    ): Response<DefaultData>

    @GET("category/get/type/")
    suspend fun selectTypeCategory(
        @Query("page")page: Int,
        @Query("size")size: Int = 20,
        @Query("type")type: Int
    ): Response<DefaultData>
}