package com.dod.whateat.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CategoryData(
    @SerializedName("seq")
    override val seq: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("count")
    val count: Int
): Serializable, SeqData