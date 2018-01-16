package com.eidea.testapplication.presentation_layer.mvp.product_page

interface ProductPageListener {
    fun onAddToBag(productId: Long, selectedColor: String)
}