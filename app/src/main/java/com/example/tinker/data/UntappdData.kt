package com.example.tinker.data

import com.google.gson.annotations.SerializedName

data class UntappdData (
    @SerializedName("response")
    val response: BreweryListData
)