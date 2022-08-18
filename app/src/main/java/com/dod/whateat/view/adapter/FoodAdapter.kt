package com.dod.whateat.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dod.whateat.data.FoodData
import com.dod.whateat.databinding.ListItemFoodBinding
import com.dod.whateat.util.DiffUtilCallback

class FoodAdapter: PagingDataAdapter<FoodData, FoodAdapter.Holder>(DiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        Holder(ListItemFoodBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position) ?: return)
    }

    inner class Holder(private val binding: ListItemFoodBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(data: FoodData){
            binding.food = data
        }
    }
}