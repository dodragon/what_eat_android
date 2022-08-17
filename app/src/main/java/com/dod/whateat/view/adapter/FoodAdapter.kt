package com.dod.whateat.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dod.whateat.data.FoodData
import com.dod.whateat.databinding.ListItemFoodBinding
import com.dod.whateat.util.DiffUtilCallback
import java.util.ArrayList

class FoodAdapter: RecyclerView.Adapter<FoodAdapter.Holder>() {

    private val list: MutableList<FoodData> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ListItemFoodBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    fun updateList(items: MutableList<FoodData>, isRefresh: Boolean){
        items.let {
            val diffCallback = DiffUtilCallback(list, items)
            val diffResult = DiffUtil.calculateDiff(diffCallback)

            list.run {
                if(isRefresh){
                    clear()
                }
                addAll(items)
                diffResult.dispatchUpdatesTo(this@FoodAdapter)
            }
        }
    }

    class Holder(private val binding: ListItemFoodBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(data: FoodData){
            binding.food = data
        }
    }
}