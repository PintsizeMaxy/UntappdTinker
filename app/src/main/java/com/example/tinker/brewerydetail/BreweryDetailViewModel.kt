package com.example.tinker.brewerydetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tinker.breweries.BreweryItem

class BreweryDetailViewModel  : ViewModel(){

    val viewState = MutableLiveData<BreweryItem>()
}