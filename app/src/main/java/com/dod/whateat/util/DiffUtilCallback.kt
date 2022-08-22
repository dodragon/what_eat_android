package com.dod.whateat.util

import androidx.recyclerview.widget.DiffUtil
import com.dod.whateat.data.SeqData

class DiffUtilCallback<T : SeqData>: DiffUtil.ItemCallback<T>() {
    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean = oldItem.seq == newItem.seq
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean = oldItem.equals(newItem.seq)
}