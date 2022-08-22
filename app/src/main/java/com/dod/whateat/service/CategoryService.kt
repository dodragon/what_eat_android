package com.dod.whateat.service

import com.dod.whateat.data.CategoryData
import com.dod.whateat.data.DefaultData
import com.google.gson.Gson

class CategoryService {

    private val maxListCnt = 50
    private val list: MutableList<CategoryData> = mutableListOf()

    init {
        for(i in 0..50){
            val range = (0..200)
            list.add(CategoryData(i, "$i 번째 카테고리", range.random()))
        }
    }

    fun categoryList(page: Int): DefaultData {
        val startIndex = if(page == 1) page - 1 else (page - 1) * 20
        var endIndex = (page * 20) - 1
        if(endIndex > maxListCnt){
            endIndex = list.size - 1
        }

        return DefaultData(page + 1, Gson().toJsonTree(list.subList(startIndex, endIndex)))
    }
}