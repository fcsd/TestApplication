package com.eidea.testapplication.presentation_layer.adapter.viewHolder

import android.support.v7.widget.RecyclerView
import android.view.View
import com.eidea.testapplication.data_layer.model.Color
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation
import kotlinx.android.synthetic.main.view_color.view.*

class ColorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun onBind(productColor: Color) {
        if (!productColor.url.isNullOrBlank()) {
            Picasso.with(itemView.context)
                    .load(productColor.url)
                    .transform(RoundedCornersTransformation(90, 10))
                    .fit()
                    .into(itemView.view_color_image_view)
        }

        itemView.view_color_check_image_view.visibility =
                if (productColor.isSelected) View.VISIBLE
                else View.INVISIBLE
    }
}
