package com.dod.whateat.service

import com.dod.whateat.data.FoodData

class FoodService {
    fun randomFoodList(): MutableList<FoodData> {
        val list = mutableListOf<FoodData>()
        for(i in 0..100){
            list.add(FoodData(i, "$i 번째 food", 0))
        }
        return list
    }

    fun foodList(categorySeq: Int): MutableList<FoodData> {
        val list = mutableListOf<FoodData>()
        for(i in 0..100){
            list.add(FoodData(i, "$i 번째 food", categorySeq))
        }
        return list
    }
}