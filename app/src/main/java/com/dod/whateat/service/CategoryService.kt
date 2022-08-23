package com.dod.whateat.service

import com.dod.whateat.data.DefaultData
import com.dod.whateat.util.RetrofitInstance
import com.google.gson.Gson

class CategoryService {

    suspend fun selectAllCategory(page: Int): DefaultData {
        val response = RetrofitInstance.categoryApi.selectAllCategory(page)
        return response.body() ?: DefaultData(-1, Gson().toJsonTree(null))
    }
}