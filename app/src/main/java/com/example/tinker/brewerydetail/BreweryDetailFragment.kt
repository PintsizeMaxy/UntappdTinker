package com.example.tinker.brewerydetail

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import androidx.navigation.fragment.navArgs
import coil.api.load
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import com.example.tinker.R
import com.example.tinker.data.BreweryData
import com.example.tinker.data.BreweryDetailData
import com.example.tinker.databinding.BreweryDetailsFragmentBinding

class BreweryDetailFragment : Fragment() {

    private val args: BreweryDetailFragmentArgs by navArgs()
    private lateinit var viewModel: BreweryDetailViewModel
    private lateinit var binding: BreweryDetailsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BreweryDetailsFragmentBinding.inflate(inflater, container, false)
        viewModel =
            ViewModelProvider(this, BreweryDetailViewModelFactory(args.breweryDetails)).get(
                BreweryDetailViewModel::class.java
            )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.viewState.observe(this, Observer { state ->
            state?.let {
                with(binding) {
                    breweryLogo.load(it.response.brewery.breweryLabel) {
                        placeholder(R.drawable.ic_photo)
                    }
                    breweryName.text = it.response.brewery.breweryName
                    type.text = it.response.brewery.breweryType
                    count.text = String.format("%d beers", it.response.brewery.beerCount)
                }
                }
        })
    }
}