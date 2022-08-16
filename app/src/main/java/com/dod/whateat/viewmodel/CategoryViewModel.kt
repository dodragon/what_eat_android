package com.dod.whateat.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.dod.whateat.data.CategoryData
import com.dod.whateat.repo.CategoryRepository
import kotlinx.coroutines.launch

class CategoryViewModel(private val repository: CategoryRepository): ViewModel() {

    val categoryList: MutableLiveData<MutableList<CategoryData>> = MutableLiveData()

    init {
        viewModelScope.launch {
            categoryList.value = repository.categoryList()
        }
    }

    /*fun selectList() = viewModelScope.launch {
        categoryList.value = repository.categoryList()
    }*/

    class CategoryFactory(): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return CategoryViewModel(CategoryRepository.getInstance()!!) as T
        }
    }
}