package com.example.tinker.data

import com.google.gson.annotations.SerializedName

data class BeerItemData (

    @SerializedName("beer")
    val beer: BeerData
)