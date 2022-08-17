package com.dod.whateat.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dod.whateat.data.CategoryData
import com.dod.whateat.databinding.ListItemCategoryBinding
import com.dod.whateat.util.DiffUtilCallback

//서버 적용 후 Paging3 고려
class CategoryAdapter: RecyclerView.Adapter<CategoryAdapter.Holder>() {

    private val list: MutableList<CategoryData> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ListItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(list[position])
        holder.itemView.setOnClickListener {
            listener?.onCategoryClick(holder.itemView, list[position].seq)
        }
    }

    override fun getItemCount(): Int = list.size

    fun updateList(items: MutableList<CategoryData>, isRefresh: Boolean){
        items.let {
            val diffCallback = DiffUtilCallback(list, items)
            val diffResult = DiffUtil.calculateDiff(diffCallback)

            list.run {
                if(isRefresh){
                    clear()
                }
                addAll(items)
                diffResult.dispatchUpdatesTo(this@CategoryAdapter)
            }
        }
    }

    interface OnCategoryClickListener {
        fun onCategoryClick(v: View, categorySeq: Int)
    }

    private var listener: OnCategoryClickListener? = null
    fun setOnCategoryClickListener(listener: OnCategoryClickListener) { this.listener = listener}

    class Holder(private val binding: ListItemCategoryBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: CategoryData) {
            binding.category = data
        }
    }
}