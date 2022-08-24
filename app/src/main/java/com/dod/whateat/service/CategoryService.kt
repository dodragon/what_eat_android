package com.dod.whateat.service

import com.dod.whateat.data.DefaultData
import com.dod.whateat.util.RetrofitInstance
import com.google.gson.Gson

class CategoryService {

    private val api = RetrofitInstance.categoryApi

    suspend fun selectAllCategory(page: Int): DefaultData {
        val response = api.selectAllCategory(page)
        return response.body() ?: DefaultData(-1, Gson().toJsonTree(null))
    }

    suspend fun selectTypeCategory(type: Int, page: Int): DefaultData {
        val response = api.selectTypeCategory(page = page, type = type)
        return response.body() ?: DefaultData(-1, Gson().toJsonTree(null))
    }
}