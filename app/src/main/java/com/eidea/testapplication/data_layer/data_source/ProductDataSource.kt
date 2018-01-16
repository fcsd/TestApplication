package com.eidea.testapplication.data_layer.data_source

import com.eidea.testapplication.data_layer.model.Product
import com.engineeringidea.mysampleapp.data_layer.requests.LoadProductRequest
import com.engineeringidea.mysampleapp.data_layer.requests.RequestCompletionHandler

class ProductDataSource : BaseDataSource<Product>() {

    override fun reloadData() {
        notifyListenersWillLoadItems()

        val loadProductRequest = LoadProductRequest()

        loadProductRequest.resumeWithCompletionHandler(
                object : RequestCompletionHandler<Product> {
                    override fun completionHandler(data: Product) {
                        this@ProductDataSource.data = data
                        notifyListenersDidLoadItems()
                    }

                    override fun completionHandlerWithError(error: String?) {
                        notifyListenersDidLoadItemsWithError(error)
                    }
                })
    }
}
