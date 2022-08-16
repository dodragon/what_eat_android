package com.dod.whateat.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dod.whateat.data.CategoryData
import com.dod.whateat.databinding.ListItemBinding

//서버 적용 후 Paging3 고려
class CategoryAdapter: RecyclerView.Adapter<CategoryAdapter.Holder>() {

    private val list: MutableList<CategoryData> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun refreshList(list: MutableList<CategoryData>){
        this.list.clear()
        this.list.addAll(list)
        for(category in this.list){
            println(category.toString())
        }
        notifyDataSetChanged()
    }

    class Holder(private val binding: ListItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: CategoryData) {
            binding.category = data
        }
    }
}