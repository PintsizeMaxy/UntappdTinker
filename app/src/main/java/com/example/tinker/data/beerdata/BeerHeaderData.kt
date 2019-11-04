package com.example.tinker.data.beerdata

import com.example.tinker.data.BeerData
import com.google.gson.annotations.SerializedName

data class BeerHeaderData(
    @SerializedName("beer")
    val beer: BeerData
)