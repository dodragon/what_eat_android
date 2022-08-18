package com.dod.whateat.util

import androidx.recyclerview.widget.DiffUtil
import com.dod.whateat.data.DefaultData

class DiffUtilCallback<T : DefaultData>: DiffUtil.ItemCallback<T>() {
    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean = oldItem.seq == newItem.seq
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean = oldItem.seq == newItem.seq
}