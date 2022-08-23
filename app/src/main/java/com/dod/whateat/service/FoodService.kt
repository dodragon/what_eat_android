package com.dod.whateat.service

import com.dod.whateat.data.DefaultData
import com.dod.whateat.data.FoodData
import com.google.gson.Gson

class FoodService {
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