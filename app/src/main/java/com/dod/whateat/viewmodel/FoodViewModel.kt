package com.dod.whateat.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.dod.whateat.data.FoodData
import com.dod.whateat.repo.FoodRepository
import com.dod.whateat.service.FoodService
import kotlinx.coroutines.launch

class FoodViewModel(private val repository: FoodRepository): ViewModel() {

    val foodList: MutableLiveData<MutableList<FoodData>> = MutableLiveData()

    fun randomSelect() = viewModelScope.launch {
        foodList.value = repository.randomFoodList()
    }

    fun foodSelect(categorySeq: Int) = repository.foodList(categorySeq)

    @Suppress("UNCHECKED_CAST")
    class FoodFactory(private val service: FoodService): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return FoodViewModel(FoodRepository.getInstance(service)!!) as T
        }
    }
}