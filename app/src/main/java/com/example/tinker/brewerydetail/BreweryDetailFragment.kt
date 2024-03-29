package com.example.tinker.brewerydetail

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import coil.api.load
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import com.example.tinker.ItemView
import com.example.tinker.R
import com.example.tinker.data.BeerItemData
import com.example.tinker.data.BeerListData
import com.example.tinker.data.BreweryData
import com.example.tinker.data.BreweryDetailData
import com.example.tinker.databinding.BreweryDetailsFragmentBinding
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.adapters.ModelAdapter

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
        val itemAdapter = ItemAdapter<BeerItemView>()
        val fastAdapter = FastAdapter.with(itemAdapter)
        viewModel.viewState.observe(this, Observer { state ->
            state?.response?.brewery?.let {
                with(binding) {
                    breweryLogo.load(it.breweryLabel) {
                        placeholder(R.drawable.ic_photo)
                    }
                    breweryName.text = it.breweryName
                    type.text = it.breweryType
                    count.text = String.format("%d beers", it.beerCount)
                    address.isVisible = it.location.breweryCity.isNotEmpty()
                    address.text = String.format(
                        "%s %s, %s",
                        it.location.breweryAddress,
                        it.location.breweryCity,
                        it.location.breweryState
                    )

                    if (it.breweryDescription.isEmpty()) descriptionBrewery.visibility = View.GONE
                    else description.text = it.breweryDescription
                    binding.beerRecycler.apply {
                        layoutManager = LinearLayoutManager(context)
                        setHasFixedSize(true)
                        adapter = fastAdapter
                        itemAdapter.add(it.beerList.items.map { BeerItemView(it.beer) })
                    }
                    fastAdapter.onClickListener = { _, _, item, _ ->
                        navigateToBeerDetail(item.model.bid)
                    }
                }
            }
        })
        binding.descriptionBrewery.setOnClickListener {
            val params = it.layoutParams
            if (params.height == ViewGroup.LayoutParams.WRAP_CONTENT) {
                params.height = 85
                binding.description.isSingleLine = true
                binding.descriptionExpand.setImageDrawable(resources.getDrawable(android.R.drawable.arrow_down_float, null))
            }
            else {
                params.height = ViewGroup.LayoutParams.WRAP_CONTENT
                binding.description.isSingleLine = false
                binding.descriptionExpand.setImageDrawable(resources.getDrawable(android.R.drawable.arrow_up_float, null))
            }
            it.layoutParams = params
        }
    }

    private fun navigateToBeerDetail(bid: Int) : Boolean {
        val actions = BreweryDetailFragmentDirections.toBeer(bid)
        findNavController().navigate(actions)
        return false
    }
}