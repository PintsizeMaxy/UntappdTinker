package com.example.tinker.beer

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tinker.BuildConfig
import com.example.tinker.client.UntappdApi
import com.example.tinker.data.BeerData
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import timber.log.Timber

class BeerViewModel(bid: Int) : ViewModel() {

    val viewScope = MutableLiveData<BeerData>()
    private var job: Job? = null

    init {
        findBeerDetails(bid)
    }

    private fun findBeerDetails(id: Int) {
        job?.cancel()

        job = viewModelScope.launch {
            try {
                val apiResult = viewModelScope.async {
                    UntappdApi.service.getBeer(id, BuildConfig.API_ID, BuildConfig.API_SECRET)
                }
                viewScope.postValue(
                    apiResult.await().response.beer
                )
            } catch (t: Throwable) {
                Timber.e(t)
            }
        }
    }
}