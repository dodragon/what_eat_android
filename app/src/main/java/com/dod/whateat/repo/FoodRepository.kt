package com.dod.whateat.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.dod.whateat.data.DefaultData
import com.dod.whateat.data.FoodData
import com.dod.whateat.service.FoodService
import com.dod.whateat.util.PagingDataSource
import kotlinx.coroutines.flow.Flow

@Suppress("UNCHECKED_CAST")
class FoodRepository(private val service: FoodService){

    fun randomFoodList() = service.randomFoodList()

    fun foodList(categorySeq: Int) = Pager(
        config = PagingConfig(pageSize = 20, enablePlaceholders = false),
        pagingSourceFactory = { PagingDataSource<FoodData>(item = service.foodList(categorySeq)) }
    ).flow as Flow<PagingData<FoodData>>

    companion object {
        private var instance: FoodRepository? = null

        fun getInstance(service: FoodService): FoodRepository? {
            if(instance == null){
                instance = FoodRepository(service)
            }
            return instance
        }
    }
}