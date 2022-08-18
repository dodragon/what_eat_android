package com.dod.whateat.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dod.whateat.repo.CategoryRepository
import com.dod.whateat.service.CategoryService

class CategoryViewModel(private val repository: CategoryRepository): ViewModel() {

    fun selectList() = repository.categoryList()

    @Suppress("UNCHECKED_CAST")
    class CategoryFactory(private val service: CategoryService): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return CategoryViewModel(CategoryRepository.getInstance(service)!!) as T
        }
    }
}