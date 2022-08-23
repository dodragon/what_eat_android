package com.dod.whateat.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CategoryData(
    @SerializedName("seq")
    override val seq: Long,
    @SerializedName("categoryName")
    val name: String,
    @SerializedName("bigType")
    val bigType: Int,
    @SerializedName("haveCount")
    val count: Int
): Serializable, SeqData