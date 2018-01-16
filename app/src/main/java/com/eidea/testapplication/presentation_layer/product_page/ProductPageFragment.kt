package com.eidea.testapplication.presentation_layer.product_page

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.eidea.testapplication.R
import com.eidea.testapplication.data_layer.data_source.DataSourceListener
import com.eidea.testapplication.data_layer.data_source.ProductDataSource
import com.eidea.testapplication.data_layer.model.Product
import com.eidea.testapplication.presentation_layer.adapter.ColorAdapter
import com.eidea.testapplication.presentation_layer.adapter.ProductImagePageAdapter
import com.vdurmont.emoji.EmojiParser
import kotlinx.android.synthetic.main.fragment_product_page.*
import java.util.*

class ProductPageFragment : Fragment() {

    private val productImagePageAdapter: ProductImagePageAdapter
    private val colorAdapter: ColorAdapter

    private val productDataSourceListener: DataSourceListener<Product>

    private val productDataSource: ProductDataSource

    private var productPageListener: ProductPageListener? = null

    private var product: Product? = null

    init {
        productImagePageAdapter = ProductImagePageAdapter()
        colorAdapter = ColorAdapter()

        productDataSource = ProductDataSource()

        productDataSourceListener = object : DataSourceListener<Product> {
            override fun notifyWillLoadItems() {

            }

            override fun notifyDidLoadItems(data: Product?) {
                product = data
                product?.let {
                    setupViews(it)

                    it.images?.let {
                        productImagePageAdapter.addPhotoList(it)
                    }


                    it.colors?.let {
                        colorAdapter.addColorList(it)
                    }
                }
            }

            override fun notifyDidLoadItemsWithError(error: String) {
                Toast.makeText(this@ProductPageFragment.context,
                        this@ProductPageFragment.context.getString(
                                R.string.default_error_text_with_message,
                                error),
                        Toast.LENGTH_SHORT)
                        .show()
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_product_page, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupImageViewPager()
        setupColorsRecyclerView()

        productDataSource.reloadData()

        productDataSource.addListener(productDataSourceListener)
    }

    override fun onDestroyView() {
        productDataSource.removeListener(productDataSourceListener)

        productImagePageAdapter.clearPhotoList()
        colorAdapter.clearColors()

        super.onDestroyView()
    }

    fun setProductPageListener(listener: ProductPageListener) {
        this.productPageListener = listener
    }

    private fun setupViews(product: Product) {

        name_text_view.text = product.name ?: ""

        val sale: Float = product.price ?: 0F

        price_text_view.text = getString(R.string.price, sale)

        description_text_view.text = prepareEmojiString(product.description ?: "")

        add_to_bag_button.setOnClickListener { addToBag() }
    }

    private fun setupColorsRecyclerView() {
        val manager = LinearLayoutManager(
                this.context,
                LinearLayoutManager.HORIZONTAL,
                false)
        color_recycler_view.layoutManager = manager
        color_recycler_view.adapter = colorAdapter
    }

    private fun setupImageViewPager() {
        image_view_pager.adapter = productImagePageAdapter
        image_view_pager.pageMargin = context.resources.getDimensionPixelOffset(R.dimen.offset_small2)
        image_view_pager_indicator.setViewPager(image_view_pager)
    }

    private fun prepareEmojiString(string: String): String {
        var prepearingString = string
        var index: Int = -1
        while ({ index = string.indexOf("U+"); index }() != -1) {
            val hexCode = string.substring(index, index + 7)
            val decimalCode = Integer.parseInt(hexCode.substring(2), 16)
            prepearingString = string.replace(hexCode, String.format(Locale.US, "&#%d;", decimalCode))
        }
        return EmojiParser.parseToUnicode(prepearingString)
    }

    private fun addToBag() {
        product?.let {
            val selectedColor: String? = it.colors?.lastOrNull { it.isSelected }?.name
            val productId = it.id
            if (selectedColor != null && productId != null) {
                productPageListener
                        ?.onAddToBag(productId,
                                selectedColor)
            } else {
                Toast.makeText(this@ProductPageFragment.context,
                        this@ProductPageFragment.context.getString(
                                R.string.default_error_select_color),
                        Toast.LENGTH_SHORT)
                        .show()
            }
        }
    }

    companion object {

        val TAG: String = "ProductPageFragment"
    }
}
