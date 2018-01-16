package com.engineeringidea.mysampleapp.data_layer.requests

import com.eidea.testapplication.data_layer.api.ProductApi
import com.eidea.testapplication.data_layer.model.Product
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoadProductRequest : BaseRequest() {

    private val service: ProductApi = retrofit.create(ProductApi::class.java)

    fun resumeWithCompletionHandler(handler: RequestCompletionHandler<Product>) {

        val call = service.product

        call.enqueue(object : Callback<Product> {
            override fun onResponse(call: Call<Product>, response: Response<Product>) {
                if (response.body() != null) {
                    val productInfo = response.body()
                    handler.completionHandler(productInfo)
                } else {
                    handler.completionHandlerWithError("Response is empty")
                }
            }

            override fun onFailure(call: Call<Product>, t: Throwable) {
                handler.completionHandlerWithError(t.message)
            }
        })
    }
}
