package com.example.tinker

import android.view.View
import com.example.tinker.data.BeerData
import com.example.tinker.data.BeerItemData
import com.example.tinker.data.BeerListData
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.items.ModelAbstractItem

open class ItemView(model: BeerData) : ModelAbstractItem<BeerData, ItemView.ViewHolder>(model){

    override val layoutRes = 0
    override val type = 0

    override fun getViewHolder(v: View) = ViewHolder(v)

    class ViewHolder(view: View) : FastAdapter.ViewHolder<ItemView>(view) {
        override fun bindView(item: ItemView, payloads: MutableList<Any>) {}

        override fun unbindView(item: ItemView) {}
    }
}