package com.example.tinker.data

import com.example.tinker.breweries.BreweryItem
import com.google.gson.annotations.SerializedName

data class BreweryData(

    @SerializedName("brewery")
    val brewery: BreweryItem
)