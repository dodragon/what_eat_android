package com.dod.whateat.service

import com.dod.whateat.data.DefaultData
import com.dod.whateat.data.FoodData
import com.dod.whateat.util.RetrofitInstance
import com.google.gson.Gson

class FoodService {

    private val api = RetrofitInstance.foodApi

    suspend fun getFoodCount(): Int {
        val response = api.getFoodCount()
        return response.body()?.data?.asInt ?: 0
    }

    fun randomFoodList(): MutableList<FoodData> {
        val list = mutableListOf<FoodData>()
        for(i in 0..100){
            list.add(FoodData(i.toLong(), "$i 번째 food", 0))
        }
        return list
    }

    fun foodList(categorySeq: Int): DefaultData {
        val list = mutableListOf<FoodData>()
        for(i in 0..100){
            list.add(FoodData(i.toLong(), "$i 번째 food", categorySeq))
        }

        return DefaultData(1, Gson().toJsonTree(list))
    }
}