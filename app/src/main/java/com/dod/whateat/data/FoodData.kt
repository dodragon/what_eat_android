package com.dod.whateat.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class FoodData(
    @SerializedName("seq")
    override val seq: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("category_seq")
    val categorySeq: Int
): Serializable, SeqData