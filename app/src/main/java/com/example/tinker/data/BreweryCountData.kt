package com.example.tinker.data

import com.google.gson.annotations.SerializedName

class BreweryCountData(

    @SerializedName("items")
    val items: Array<BreweryData>
)