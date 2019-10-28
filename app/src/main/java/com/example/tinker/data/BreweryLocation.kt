package com.example.tinker.data

import com.google.gson.annotations.SerializedName

data class BreweryLocation (

    @SerializedName("brewery_address")
    val breweryAddress: String,
    @SerializedName("brewery_city")
    val breweryCity: String,
    @SerializedName("brewery_state")
    val breweryState: String
)