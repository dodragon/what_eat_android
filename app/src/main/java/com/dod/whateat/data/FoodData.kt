package com.dod.whateat.data

import com.google.gson.annotations.SerializedName

data class FoodData(
    @SerializedName("seq")
    val seq: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("category_seq")
    val categorySeq: Int
)
