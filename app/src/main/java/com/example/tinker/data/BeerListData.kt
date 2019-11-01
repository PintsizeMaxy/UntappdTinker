package com.example.tinker.data

import com.google.gson.annotations.SerializedName

data class BeerListData (

    @SerializedName("items")
    val items: List<BeerItemData>
)