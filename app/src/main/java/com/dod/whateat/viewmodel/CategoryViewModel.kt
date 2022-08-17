package com.dod.whateat.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagingData
import com.dod.whateat.data.CategoryData
import com.dod.whateat.repo.CategoryRepository
import com.dod.whateat.service.CategoryService
import kotlinx.coroutines.flow.Flow

class CategoryViewModel(private val repository: CategoryRepository): ViewModel() {

    fun selectList(): Flow<PagingData<CategoryData>> {
        return repository.categoryList()
    }

    @Suppress("UNCHECKED_CAST")
    class CategoryFactory(private val service: CategoryService): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return CategoryViewModel(CategoryRepository.getInstance(service)!!) as T
        }
    }
}