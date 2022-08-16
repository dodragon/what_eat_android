package com.dod.whateat.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.dod.whateat.data.FoodData
import com.dod.whateat.repo.FoodRepository
import kotlinx.coroutines.launch

class RandomFoodViewModel(private val repository: FoodRepository): ViewModel() {

    val foodList: MutableLiveData<MutableList<FoodData>> = MutableLiveData()

    init {
        viewModelScope.launch {
            foodList.value = repository.randomFoodList()
        }
    }

    class RandomFoodFactory(private val application: Application): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return RandomFoodViewModel(FoodRepository.getInstance(application)!!) as T
        }
    }
}