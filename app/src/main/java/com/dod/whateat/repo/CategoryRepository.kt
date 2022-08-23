package com.dod.whateat.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.dod.whateat.data.CategoryData
import com.dod.whateat.api.CategoryApi
import com.dod.whateat.service.CategoryService
import com.dod.whateat.util.PagingDataSource
import com.dod.whateat.util.RetrofitInstance
import kotlinx.coroutines.flow.Flow

@Suppress("UNCHECKED_CAST")
class CategoryRepository(private val service: CategoryService) {

    suspend fun categoryList(page: Int): Flow<PagingData<CategoryData>>{
        service.selectAllCategory(page).let {
            return Pager(
                config = PagingConfig(pageSize = 20, enablePlaceholders = false),
                pagingSourceFactory = { PagingDataSource(item = it, classType = CategoryData::class.java) }
            ).flow as Flow<PagingData<CategoryData>>
        }
    }

    companion object {
        private var instance: CategoryRepository? = null

        fun getInstance(service: CategoryService): CategoryRepository? {
            if(instance == null){
                instance = CategoryRepository(service)
            }
            return instance
        }
    }
}