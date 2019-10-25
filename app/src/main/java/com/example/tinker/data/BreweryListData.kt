package com.example.tinker.data

import com.google.gson.annotations.SerializedName

data class BreweryListData (
    @SerializedName("brewery")
    val brewery: BreweryCountData
)