package com.dod.whateat.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dod.whateat.R
import com.dod.whateat.databinding.ActivityListBinding
import com.dod.whateat.view.adapter.CategoryAdapter
import com.dod.whateat.viewmodel.CategoryViewModel

class ListActivity : AppCompatActivity() {

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
        categoryAdapter = CategoryAdapter()

        binding.recycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = categoryAdapter
        }
    }

    private fun setObserver(){
        viewModel.categoryList.observe(this) {
            categoryAdapter.refreshList(viewModel.categoryList.value!!)
        }
    }
}