package com.eidea.testapplication.data_layer.api

import com.eidea.testapplication.data_layer.model.Product
import retrofit2.Call
import retrofit2.http.GET

interface ProductApi {
    @get:GET("59c3864c1100000f0199cb28/")
    val product: Call<Product>
}