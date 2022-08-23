package com.dod.whateat.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.dod.whateat.data.CategoryData
import com.dod.whateat.data.DefaultData
import com.dod.whateat.service.CategoryApi
import com.dod.whateat.util.PagingDataSource
import com.dod.whateat.util.RetrofitInstance
import kotlinx.coroutines.flow.Flow

@Suppress("UNCHECKED_CAST")
class CategoryRepository(private val service: CategoryApi) {

    suspend fun categoryList(page: Int): Flow<PagingData<CategoryData>>{
        val response = RetrofitInstance.categoryApi.selectAllCategory()
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = { PagingDataSource<CategoryData>(item = response.body()!!) }
        ).flow as Flow<PagingData<CategoryData>>
    }

    companion object {
        private var instance: CategoryRepository? = null

        fun getInstance(service: CategoryApi): CategoryRepository? {
            if(instance == null){
                instance = CategoryRepository(service)
            }
            return instance
        }
    }
}