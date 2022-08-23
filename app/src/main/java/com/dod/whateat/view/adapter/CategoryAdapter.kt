package com.dod.whateat.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dod.whateat.data.CategoryData
import com.dod.whateat.databinding.ListItemCategoryBinding
import com.dod.whateat.util.DiffUtilCallback

class CategoryAdapter: PagingDataAdapter<CategoryData, CategoryAdapter.Holder>(DiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        Holder(ListItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = getItem(position) ?: return
        holder.bind(item)
        holder.itemView.setOnClickListener {
            listener?.onCategoryClick(holder.itemView, item.seq)
        }
    }

    interface OnCategoryClickListener {
        fun onCategoryClick(v: View, categorySeq: Long)
    }

    private var listener: OnCategoryClickListener? = null
    fun setOnCategoryClickListener(listener: OnCategoryClickListener) { this.listener = listener}

    inner class Holder(private val binding: ListItemCategoryBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: CategoryData) {
            binding.category = data
        }
    }
}