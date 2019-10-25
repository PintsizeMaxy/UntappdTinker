package com.example.tinker.brewerydetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tinker.client.UntappdApi
import com.example.tinker.data.UntappdDetailData
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import timber.log.Timber

class BreweryDetailViewModel(brewery: Int) : ViewModel() {

    val viewState = MutableLiveData<UntappdDetailData>()
    var job: Job? = null

    init {
        fetchBreweryDetails(brewery)
    }

    private fun fetchBreweryDetails(id: Int) {
        job?.cancel()

        job = viewModelScope.launch {
            try {
                val apiResult = viewModelScope.async {
                    UntappdApi.service.getBrewery(
                        id,
                        UntappdApi.clientId,
                        UntappdApi.clientSecret)
                }
                viewState.postValue(
                    apiResult.await()
                )
            } catch (t: Throwable) {
                Timber.e(t)
            }
        }
    }
}