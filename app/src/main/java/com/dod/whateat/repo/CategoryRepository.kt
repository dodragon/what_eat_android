package com.dod.whateat.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.dod.whateat.data.CategoryData
import com.dod.whateat.repo.paging.CategoryPagingSource
import com.dod.whateat.service.CategoryService
import kotlinx.coroutines.flow.Flow

class CategoryRepository(private val service: CategoryService) {

    fun categoryList(): Flow<PagingData<CategoryData>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = { CategoryPagingSource(service = service) }
        ).flow
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