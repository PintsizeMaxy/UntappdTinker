package com.example.tinker.breweries

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tinker.databinding.BreweryRecyclerBinding
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter

class BreweryFragment : Fragment() {

    private lateinit var binding: BreweryRecyclerBinding
    private val viewModel: BreweryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BreweryRecyclerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val itemAdapter = ItemAdapter<BreweryItem>()
        val fastAdapter = FastAdapter.with(itemAdapter)
        viewModel.viewState.observe(this, Observer { state ->
            val items = ArrayList<BreweryItem>()
            for(item in state.response.brewery.items){
                items.add(item.brewery)
            }
            binding.listRecycler.apply {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = fastAdapter
                itemAdapter.add(items)
            }
        })
        fastAdapter.onClickListener = { _, _, item, _ ->
//            binding.searchBreweries.clearFocus()
            navigateToDetails(item)
        }

        view.setOnTouchListener { _, _ ->
            binding.searchBreweries.clearFocus()
            itemAdapter.clear()
            viewModel.searchBreweries(binding.searchBreweries.text.toString())
            true
        }
//        binding.searchBreweries.doOnTextChanged { text, _, _, _ ->
//            itemAdapter.clear()
//            viewModel.searchBreweries(text.toString())
//        }
        binding.searchBreweries.doAfterTextChanged {
            itemAdapter.clear()

        }
    }

    private fun navigateToDetails(item : BreweryItem) : Boolean {
        val action = BreweryFragmentDirections.actionBreweryFragmentToBreweryDetailFragment2(item.breweryId)
        findNavController().navigate(action)
        return false
    }
}