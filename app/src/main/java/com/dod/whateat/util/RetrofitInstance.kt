package com.dod.whateat.util

import com.dod.whateat.api.CategoryApi
import com.dod.whateat.api.FoodApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitInstance {

    private const val BASE_URL = "http://192.168.0.33:8080/"

    private val retrofit by lazy {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val categoryApi: CategoryApi by lazy { retrofit.create(CategoryApi::class.java) }
    val foodApi: FoodApi by lazy { retrofit.create(FoodApi::class.java) }
}