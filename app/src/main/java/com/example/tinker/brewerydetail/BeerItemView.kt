package com.example.tinker.brewerydetail

import android.view.View
import coil.api.clear
import coil.api.load
import com.example.tinker.ItemView
import com.example.tinker.R
import com.example.tinker.data.BeerData
import com.example.tinker.data.BeerItemData
import kotlinx.android.synthetic.main.beer_item.view.*

class BeerItemView(private val beer: BeerData) : ItemView(beer) {
    override val layoutRes = R.layout.beer_item
    override val type = R.id.beer_item

    override fun getViewHolder(v: View) = ViewHolder(v)

    override fun bindView(holder: ViewHolder, payloads: MutableList<Any>) {
        super.bindView(holder, payloads)
        with(beer){
            holder.itemView.beer_label.load(beerLabel){
                placeholder(R.drawable.ic_photo)
            }
            holder.itemView.beer_name.text = beerName
            holder.itemView.beer_style.text = beerStyle
            holder.itemView.beer_rating.text = ratingScore.toString()
        }
    }

    override fun unbindView(holder: ViewHolder) {
        super.unbindView(holder)
        holder.itemView.beer_label.clear()
        holder.itemView.beer_name.text = null
        holder.itemView.beer_style.text = null
        holder.itemView.beer_rating.text = null
    }
}