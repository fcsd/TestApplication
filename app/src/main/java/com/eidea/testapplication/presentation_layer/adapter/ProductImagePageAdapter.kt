package com.eidea.testapplication.presentation_layer.adapter

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eidea.testapplication.R
import com.eidea.testapplication.data_layer.model.Image
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation
import kotlinx.android.synthetic.main.view_image_page.view.*
import java.util.*

class ProductImagePageAdapter : PagerAdapter() {

    private val imageList: MutableList<Image>

    init {
        imageList = ArrayList()
    }

    fun addPhotoList(imageList: List<Image>) {
        this.imageList.addAll(imageList)
        this.notifyDataSetChanged()
    }

    fun clearPhotoList() {
        imageList.clear()
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = container.context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val layout = inflater.inflate(R.layout.view_image_page, container, false) as ViewGroup

        val imageView = layout.view_image_page_image_view
        imageView.setBackgroundColor(0)
        Picasso.with(imageView.context)
                .load(imageList[position].url)
                .fit()
                .centerInside()
                .transform(RoundedCornersTransformation(30, 5))
                .into(imageView)

        container.addView(layout)
        return layout
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun getItemPosition(`object`: Any?): Int {
        return PagerAdapter.POSITION_NONE
    }

    override fun getCount(): Int {
        return imageList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }
}
