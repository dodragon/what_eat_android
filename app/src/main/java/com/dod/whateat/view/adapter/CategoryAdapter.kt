package com.dod.whateat.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dod.whateat.data.CategoryData
import com.dod.whateat.databinding.ListItemCategoryBinding

class CategoryAdapter: PagingDataAdapter<CategoryData, CategoryAdapter.Holder>(
    object: DiffUtil.ItemCallback<CategoryData>() {
        override fun areContentsTheSame(oldItem: CategoryData, newItem: CategoryData): Boolean {
            return oldItem.seq == newItem.seq
        }
        override fun areItemsTheSame(oldItem: CategoryData, newItem: CategoryData): Boolean {
            return oldItem.seq == newItem.seq && oldItem == newItem
        }
    }
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ListItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = getItem(position) ?: return
        holder.bind(item)
        holder.itemView.setOnClickListener {
            listener?.onCategoryClick(holder.itemView, item.seq)
        }
    }

    interface OnCategoryClickListener {
        fun onCategoryClick(v: View, categorySeq: Int)
    }

    private var listener: OnCategoryClickListener? = null
    fun setOnCategoryClickListener(listener: OnCategoryClickListener) { this.listener = listener}

    inner class Holder(private val binding: ListItemCategoryBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: CategoryData) {
            binding.category = data
        }
    }
}