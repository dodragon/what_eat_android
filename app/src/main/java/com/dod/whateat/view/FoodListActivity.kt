package com.dod.whateat.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.dod.whateat.databinding.ActivityFoodListBinding
import com.dod.whateat.service.FoodService
import com.dod.whateat.view.adapter.FoodAdapter
import com.dod.whateat.viewmodel.FoodViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class FoodListActivity : AppCompatActivity() {

    private val binding by lazy { ActivityFoodListBinding.inflate(layoutInflater) }
    private val viewModel by lazy { ViewModelProvider(this, FoodViewModel.FoodFactory(
        FoodService()))[FoodViewModel::class.java] }

    private lateinit var foodAdapter: FoodAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setView()
        callList(intent.getIntExtra("seq", 0))
    }

    private fun setView(){
        foodAdapter = FoodAdapter()

        binding.recycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = foodAdapter
        }
    }

    private fun callList(seq: Int){
        lifecycleScope.launchWhenStarted {
            viewModel.foodSelect(seq).collect {
                foodAdapter.submitData(it)
            }
        }

        lifecycleScope.launch {
            foodAdapter.loadStateFlow.collectLatest { loadStates ->
                binding.progress.isVisible = loadStates.refresh is LoadState.Loading
            }
        }
    }
}