package com.example.tinker.data

import com.google.gson.annotations.SerializedName

data class BreweryDetailData(

    @SerializedName("bid")
    val bid: Int,
    @SerializedName("brewery_name")
    val breweryName: String,
    @SerializedName("brewery_label")
    val breweryLabel: String,
    @SerializedName("beer_count")
    val beerCount: Int,
    @SerializedName("brewery_type")
    val breweryType: String,
    @SerializedName("brewery_description")
    val breweryDescription: String,
    @SerializedName("location")
    val location: BreweryLocation,
    @SerializedName("beer_list")
    val beerList: BeerListData
)