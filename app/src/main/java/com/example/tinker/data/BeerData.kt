package com.example.tinker.data

import com.google.gson.annotations.SerializedName

data class BeerData (

    @SerializedName("beer_name")
    val beerName: String,
    @SerializedName("beer_label")
    val beerLabel: String,
    @SerializedName("beer_style")
    val beerStyle: String,
    @SerializedName("beer_abv")
    val beerAbv: Double,
    @SerializedName("beer_ibu")
    val beerIbu: Double,
    @SerializedName("rating_score")
    val ratingScore: Double

)