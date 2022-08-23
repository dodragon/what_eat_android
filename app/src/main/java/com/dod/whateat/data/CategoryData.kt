package com.dod.whateat.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CategoryData(
    @SerializedName("seq")
    override val seq: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("type")
    val bigType: Int,
    @SerializedName("haveCount")
    val count: Int
): Serializable, SeqData