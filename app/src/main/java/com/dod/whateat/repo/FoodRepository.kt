package com.dod.whateat.repo

import com.dod.whateat.data.FoodData

//서버 연동시 수정 필요
class FoodRepository() {

    suspend fun randomFoodList(): MutableList<FoodData> {
        val list = mutableListOf<FoodData>()
        for(i in 0..100){
            list.add(makeFood(i, "$i 번째 food"))
        }
        return list
    }

    suspend fun foodList(categorySeq: Int): MutableList<FoodData> {
        val list = mutableListOf<FoodData>()
        for(i in 0..100){
            list.add(FoodData(i, "$i 번째 food", categorySeq))
        }
        return list
    }

    private fun makeFood(seq: Int, name: String): FoodData{
        return FoodData(seq, name, 0);
    }

    companion object {
        private var instance: FoodRepository? = null

        fun getInstance(): FoodRepository? {
            if(instance == null){
                instance = FoodRepository()
            }
            return instance
        }
    }
}