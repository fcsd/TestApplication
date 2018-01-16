package com.eidea.testapplication.data_layer.model

import com.google.gson.annotations.SerializedName

class Color {

    @SerializedName("name")
    val name: String? = null
    @SerializedName("url")
    val url: String? = null

    var isSelected: Boolean = false
}