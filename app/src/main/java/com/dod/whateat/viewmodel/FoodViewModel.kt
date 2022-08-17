package com.dod.whateat.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.dod.whateat.data.FoodData
import com.dod.whateat.repo.FoodRepository
import kotlinx.coroutines.launch

class FoodViewModel(private val repository: FoodRepository): ViewModel() {

    val foodList: MutableLiveData<MutableList<FoodData>> = MutableLiveData()

    fun randomSelect() = viewModelScope.launch {
        foodList.value = repository.randomFoodList()
    }

    fun foodSelect(categorySeq: Int) = viewModelScope.launch {
        foodList.value = repository.foodList(categorySeq)
    }

    class FoodFactory(private val application: Application): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return FoodViewModel(FoodRepository.getInstance(application)!!) as T
        }
    }
}