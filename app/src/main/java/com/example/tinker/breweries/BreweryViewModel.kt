package com.example.tinker.breweries

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tinker.client.UntappdApi
import com.example.tinker.data.UntappdData
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import timber.log.Timber

class BreweryViewModel : ViewModel() {

    val viewState = MutableLiveData<UntappdData>()
    var job: Job? = null

    fun searchBreweries(search: String) {
        job?.cancel()

        job = viewModelScope.launch {
            try {
                val apiResult = viewModelScope.async {
                    UntappdApi.service.searchBrewery(
                        search,
                        UntappdApi.clientId,
                        UntappdApi.clientSecret
                    )
                }
                viewState.postValue(
                    apiResult.await()
                )
            }catch (t: Throwable){
                Timber.e(t)
            }
        }
    }
}