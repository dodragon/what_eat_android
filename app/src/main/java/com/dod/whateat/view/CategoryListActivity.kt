package com.dod.whateat.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dod.whateat.R
import com.dod.whateat.databinding.ActivityListBinding
import com.dod.whateat.view.adapter.CategoryAdapter
import com.dod.whateat.viewmodel.CategoryViewModel

class CategoryListActivity : AppCompatActivity() {

    private val binding by lazy { ActivityListBinding.inflate(layoutInflater) }
    private val viewModel by lazy { ViewModelProvider(this, CategoryViewModel.CategoryFactory())[CategoryViewModel::class.java] }

    private lateinit var categoryAdapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setView()
        setObserver()
    }

    private fun setView(){
        categoryAdapter = CategoryAdapter().apply {
            setOnCategoryClickListener(object: CategoryAdapter.OnCategoryClickListener{
                override fun onCategoryClick(v: View, categorySeq: Int) {
                    val intent =  Intent(this@CategoryListActivity, FoodListActivity::class.java)
                    intent.putExtra("seq", categorySeq)
                    startActivity(intent)
                }
            })
        }

        binding.recycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = categoryAdapter
        }
    }

    private fun setObserver(){
        viewModel.categoryList.observe(this) {
            categoryAdapter.updateList(viewModel.categoryList.value!!, true)
        }
    }
}