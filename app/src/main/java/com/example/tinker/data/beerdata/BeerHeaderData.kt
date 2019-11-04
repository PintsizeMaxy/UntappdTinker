package com.example.tinker.data.beerdata

import com.google.gson.annotations.SerializedName

data class BeerHeaderData(
    @SerializedName("beer")
    val beer: BeerDetailData
)