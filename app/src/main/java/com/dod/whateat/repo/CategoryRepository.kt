package com.dod.whateat.repo

import com.dod.whateat.data.CategoryData

class CategoryRepository {

    suspend fun categoryList(): MutableList<CategoryData> {
        val list = mutableListOf<CategoryData>()
        for(i in 0..50){
            val range = (0..200)
            list.add(CategoryData(i, "$i 번째 카테고리", range.random()))
        }
        return list;
    }

    companion object {
        private var instance: CategoryRepository? = null

        fun getInstance(): CategoryRepository? {
            if(instance == null){
                instance = CategoryRepository()
            }
            return instance
        }
    }
}