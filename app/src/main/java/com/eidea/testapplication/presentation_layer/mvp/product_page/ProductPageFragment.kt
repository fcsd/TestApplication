package com.eidea.testapplication.presentation_layer.mvp.product_page

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.eidea.testapplication.R
import com.eidea.testapplication.data_layer.model.Product
import com.eidea.testapplication.presentation_layer.adapter.ColorAdapter
import com.eidea.testapplication.presentation_layer.adapter.ProductImagePageAdapter
import kotlinx.android.synthetic.main.fragment_product_page.*

class ProductPageFragment : Fragment(), ProductPageContract.View {

    override var presenter: ProductPageContract.Presenter = ProductPagePresenter(this)

    override val isActive: Boolean = false

    private val productImagePageAdapter: ProductImagePageAdapter = ProductImagePageAdapter()

    private val colorAdapter: ColorAdapter = ColorAdapter()

    private var productPageListener: ProductPageListener? = null

    /*
    Fragment lifecycle
     */
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_product_page, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.start()
    }

    override fun onDestroyView() {

        productImagePageAdapter.clearPhotoList()
        colorAdapter.clearColors()

        presenter.stop()

        super.onDestroyView()
    }

    /*
    Public
     */
    fun setProductPageListener(listener: ProductPageListener) {
        this.productPageListener = listener
    }

    /*
    Private
     */
    private fun initColorsRecyclerView() {
        val manager = LinearLayoutManager(
                this.context,
                LinearLayoutManager.HORIZONTAL,
                false)
        color_recycler_view.layoutManager = manager
        color_recycler_view.adapter = colorAdapter
    }

    private fun initImageViewPager() {
        image_view_pager.adapter = productImagePageAdapter
        image_view_pager.pageMargin = context.resources.getDimensionPixelOffset(R.dimen.offset_small2)
        image_view_pager_indicator.setViewPager(image_view_pager)
    }

    /*
    ProductPageContract.View
     */
    override fun initViews() {
        initColorsRecyclerView()
        initImageViewPager()
    }

    override fun setupViews(product: Product) {

        product.images?.let {
            productImagePageAdapter.addPhotoList(it)
        }

        product.colors?.let {
            colorAdapter.addColorList(it)
        }

        name_text_view.text = product.name ?: ""

        val sale: Float = product.price ?: 0F

        price_text_view.text = getString(R.string.price, sale)

        description_text_view.text = product.description ?: ""

        add_to_bag_button.setOnClickListener { presenter.addToBag() }
    }

    override fun onAddToBag(productId: Long, selectedColor: String) {
        productPageListener?.onAddToBag(productId, selectedColor)
    }

    override fun showMessage(message: String) {

        Toast.makeText(this.context,
                message,
                Toast.LENGTH_SHORT)
                .show()
    }

    override fun showMessage(messageId: Int) {
        showMessage(getString(messageId))
    }

    override fun showErrorMessage(message: String) {

        Toast.makeText(this.context,
                getString(R.string.default_error_text_with_message, message),
                Toast.LENGTH_SHORT)
                .show()
    }

    companion object {

        val TAG: String = "ProductPageFragment"
    }
}
