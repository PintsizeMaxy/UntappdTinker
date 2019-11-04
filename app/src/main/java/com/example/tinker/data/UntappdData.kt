package com.example.tinker.data

import com.example.tinker.data.beerdata.BeerHeaderData
import com.google.gson.annotations.SerializedName

data class UntappdData (
    @SerializedName("response")
    val response: BreweryListData
)

data class UntappdDetailData (
    @SerializedName("response")
    val response: BreweryDetail
)

data class UntappdBeerData(
    @SerializedName("response")
    val response: BeerHeaderData
)