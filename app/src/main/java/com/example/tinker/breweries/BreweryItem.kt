package com.example.tinker.breweries

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import coil.api.clear
import coil.api.load
import com.example.tinker.R
import com.google.gson.annotations.SerializedName
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.items.AbstractItem

class BreweryItem : AbstractItem<BreweryItem.ViewHolder>() {

    @SerializedName("brewery_id")
    val breweryId: Int = 0
    @SerializedName("brewery_name")
    val breweryName: String = ""
    @SerializedName("brewery_label")
    val breweryLabel: String = ""

    override val layoutRes = R.layout.brewery_item
    override val type = R.id.brewery_item

    override fun getViewHolder(v: View) = ViewHolder(v)


    class ViewHolder(view: View) : FastAdapter.ViewHolder<BreweryItem>(view){
        var image = view.findViewById<AppCompatImageView>(R.id.brewery_logo)
        var name = view.findViewById<AppCompatTextView>(R.id.brewery_name)

        override fun bindView(item: BreweryItem, payloads: MutableList<Any>) {
            image.load(item.breweryLabel){
                placeholder(R.drawable.ic_photo)
            }
            name.text = item.breweryName
        }

        override fun unbindView(item: BreweryItem) {
            name.text = null
            image.clear()
        }

    }
}