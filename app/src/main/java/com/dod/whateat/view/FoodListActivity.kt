package com.dod.whateat.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dod.whateat.R
import com.dod.whateat.databinding.ActivityFoodListBinding
import com.dod.whateat.view.adapter.FoodAdapter
import com.dod.whateat.viewmodel.FoodViewModel

class FoodListActivity : AppCompatActivity() {

    private val binding by lazy { ActivityFoodListBinding.inflate(layoutInflater) }
    private val viewModel by lazy { ViewModelProvider(this, FoodViewModel.FoodFactory(application))[FoodViewModel::class.java] }

    private lateinit var foodAdapter: FoodAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setView()
        setObserver()
        callData(intent.getIntExtra("seq", 0))
    }

    private fun setView(){
        foodAdapter = FoodAdapter()

        binding.recycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = foodAdapter
        }
    }

    private fun callData(seq: Int){
        viewModel.foodSelect(seq)
    }

    private fun setObserver(){
        viewModel.foodList.observe(this) {
            foodAdapter.addList(it)
        }
    }
}