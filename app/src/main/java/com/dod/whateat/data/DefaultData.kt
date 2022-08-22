package com.dod.whateat.data

import com.google.gson.JsonElement
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class DefaultData (
    @SerializedName("next_page")
    var nextPage: Int,
    @SerializedName("data")
    var data: JsonElement
): Serializable