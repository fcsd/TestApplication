package com.eidea.testapplication.presentation_layer.mvp.product_page

import com.eidea.testapplication.R
import com.eidea.testapplication.data_layer.data_source.DataSourceListener
import com.eidea.testapplication.data_layer.data_source.ProductDataSource
import com.eidea.testapplication.data_layer.model.Product
import java.lang.ref.WeakReference

class ProductPagePresenter(productPageView: ProductPageContract.View) : ProductPageContract.Presenter {

    private val productPageView: WeakReference<ProductPageContract.View> = WeakReference(productPageView)

    private val productDataSourceListener: DataSourceListener<Product>

    private val productDataSource: ProductDataSource = ProductDataSource()

    var product: Product? = null

    private fun view(): ProductPageContract.View? {
        return productPageView.get()
    }

    init {
        view()?.presenter = this

        productDataSourceListener = object : DataSourceListener<Product> {
            override fun notifyWillLoadItems() {

            }

            override fun notifyDidLoadItems(data: Product?) {
                product = data
                product?.let {
                    view()?.setupViews(it)
                }
            }

            override fun notifyDidLoadItemsWithError(error: String) {
                productPageView.showErrorMessage(error)
            }
        }
    }

    /*
    ProductPageContract.Presenter
     */
    override fun start() {
        productDataSource.addListener(productDataSourceListener)
        productDataSource.reloadData()

        view()?.initViews()
    }

    override fun stop() {
        productPageView.clear()
    }

    override fun addToBag() {
        product?.let {
            val selectedColor: String? = it.colors?.lastOrNull { it.isSelected }?.name
            val productId = it.id
            if (selectedColor != null && productId != null) {
                view()?.onAddToBag(productId, selectedColor)
            } else {
                view()?.showMessage(R.string.default_error_select_color)
            }
        }

    }
}