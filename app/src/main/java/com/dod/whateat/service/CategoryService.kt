package com.dod.whateat.service

import com.dod.whateat.data.CategoryData

class CategoryService {
    fun categoryList(): MutableList<CategoryData> {
        val list = mutableListOf<CategoryData>()
        for(i in 0..50){
            val range = (0..200)
            list.add(CategoryData(i, "$i 번째 카테고리", range.random()))
        }
        return list
    }
}