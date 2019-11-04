package com.example.tinker.beer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class BeerViewModelFactory(private val bid: Int): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return BeerViewModel(bid) as T
    }
}