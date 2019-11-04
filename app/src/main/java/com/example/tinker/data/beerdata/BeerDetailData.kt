package com.example.tinker.data.beerdata

import com.google.gson.annotations.SerializedName

data class BeerDetailData (

    @SerializedName("beer_name")
    val beerName: String,
    @SerializedName("beer_label")
    val beerLabel: String,
    @SerializedName("beer_abv")
    val beerAbv: Double,
    @SerializedName("beer_description")
    val description: String,
    @SerializedName("beer_style")
    val style: String,
    @SerializedName("rating_score")
    val score: Double
)