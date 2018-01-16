package com.eidea.testapplication.data_layer.model

import com.eidea.testapplication.data_layer.model.Color
import com.eidea.testapplication.data_layer.model.Image
import com.google.gson.annotations.SerializedName


class Product {

    @SerializedName("id")
    val id: Long? = null
    @SerializedName("name")
    val name: String? = null
    @SerializedName("description")
    val description: String? = null
    @SerializedName("price")
    val price: Float? = null
    @SerializedName("images")
    val images: List<Image>? = null
    @SerializedName("colors")
    val colors: List<Color>? = null

}
