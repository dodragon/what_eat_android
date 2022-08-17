package com.dod.whateat.util

import androidx.recyclerview.widget.DiffUtil
import com.dod.whateat.data.DefaultData

class DiffUtilCallback(private val oldList: List<Any>, private val newList: List<Any>): DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = compareItem(oldItemPosition, newItemPosition)

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = compareItem(oldItemPosition, newItemPosition)

    private fun compareItem(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]

        return if(oldItem is DefaultData && newItem is DefaultData){
            oldItem.seq == newItem.seq
        }else {
            false
        }
    }
}