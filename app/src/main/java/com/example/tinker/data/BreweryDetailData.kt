package com.example.tinker.data

import com.google.gson.annotations.SerializedName

data class BreweryDetailData(

    @SerializedName("brewery_name")
    val breweryName: String,
    @SerializedName("brewery_label")
    val breweryLabel: String,
    @SerializedName("beer_count")
    val beerCount: Int,
    @SerializedName("brewery_type")
    val breweryType: String
)