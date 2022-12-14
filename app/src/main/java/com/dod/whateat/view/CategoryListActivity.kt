package com.dod.whateat.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.*
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dod.whateat.databinding.ActivityCategoryListBinding
import com.dod.whateat.service.CategoryService
import com.dod.whateat.view.adapter.CategoryAdapter
import com.dod.whateat.viewmodel.CategoryViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CategoryListActivity : AppCompatActivity() {

    private val binding by lazy { ActivityCategoryListBinding.inflate(layoutInflater) }
    private val viewModel by lazy {
        ViewModelProvider(this, CategoryViewModel.CategoryFactory(CategoryService()))[CategoryViewModel::class.java]
    }

    private var page: Int = 0

    private lateinit var categoryAdapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setView()
        callList()
    }

    private fun setView(){
        categoryAdapter = CategoryAdapter().apply {
            setOnCategoryClickListener(object: CategoryAdapter.OnCategoryClickListener{
                override fun onCategoryClick(v: View, categorySeq: Long) {
                    val intent =  Intent(this@CategoryListActivity, FoodListActivity::class.java)
                    intent.putExtra("seq", categorySeq)
                    startActivity(intent)
                }
            })
        }

        binding.recycler.apply {
            layoutManager = LinearLayoutManager(this@CategoryListActivity, RecyclerView.VERTICAL, false)
            adapter = categoryAdapter
        }
    }

    private fun callList(){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.selectList(page).collect {
                    categoryAdapter.submitData(it)
                    page++
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                categoryAdapter.loadStateFlow.collectLatest { loadStates ->
                    binding.progress.isVisible = loadStates.refresh is LoadState.Loading
                }
            }
        }
    }
}