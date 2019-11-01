package com.example.tinker.breweries

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tinker.R
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
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val itemAdapter = ItemAdapter<BreweryItem>()
        val fastAdapter = FastAdapter.with(itemAdapter)
        viewModel.viewState.observe(this, Observer { state ->
            itemAdapter.clear()
            val items = ArrayList<BreweryItem>()
            state.response.brewery.items.forEach { items.add(it.brewery) }
            binding.listRecycler.apply {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = fastAdapter
                itemAdapter.add(items)
            }
        })
        fastAdapter.onClickListener = { _, _, item, _ ->
            navigateToDetails(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.options_menu, menu)
        val searchItem = menu.findItem(R.id.search).actionView as SearchView
        searchItem.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchItem.clearFocus()
                searchItem.setQuery(query, false)
                viewModel.searchBreweries(query.toString())
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun navigateToDetails(item: BreweryItem): Boolean {
        val action =
            BreweryFragmentDirections.actionBreweryFragmentToBreweryDetailFragment2(item.breweryId)
        findNavController().navigate(action)
        return false
    }
}