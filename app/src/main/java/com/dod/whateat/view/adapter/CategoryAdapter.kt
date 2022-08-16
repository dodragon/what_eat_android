package com.dod.whateat.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dod.whateat.data.CategoryData
import com.dod.whateat.databinding.ListItemBinding

class CategoryAdapter: RecyclerView.Adapter<CategoryAdapter.Holder>() {

    private val list: MutableList<CategoryData> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(list[position])asdf
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class Holder(private val binding: ListItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: CategoryData) {
            binding.category = data
        }
    }
}