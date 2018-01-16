package com.eidea.testapplication.presentation_layer.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.eidea.testapplication.R
import com.eidea.testapplication.data_layer.model.Color
import com.eidea.testapplication.presentation_layer.adapter.viewHolder.ColorViewHolder
import java.util.*

class ColorAdapter : RecyclerView.Adapter<ColorViewHolder>() {

    private val colors: MutableList<Color>

    init {
        colors = ArrayList()
    }

    fun addColorList(colors: List<Color>) {
        this.colors.addAll(colors)
        this.notifyDataSetChanged()
    }

    fun clearColors() {
        colors.clear()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.view_color, parent, false)
        return ColorViewHolder(view)
    }

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        holder.onBind(colors[position])
        holder.itemView.setOnClickListener {
            colors.forEach { it.isSelected = false }
            colors[position].isSelected = true
            this@ColorAdapter.notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return colors.size
    }
}
