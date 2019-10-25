package com.example.tinker.breweries

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tinker.databinding.BreweryRecyclerBinding
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import kotlinx.android.synthetic.main.brewery_item.view.*

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
        fastAdapter.onClickListener = { v, _, item, _ ->
            navigateToDetails(v!!, item)
        }
        binding.searchBreweries.doAfterTextChanged {
            itemAdapter.clear()
            it?.let { viewModel.searchBreweries(it.toString()) }
        }
    }

    private fun navigateToDetails(v: View, item : BreweryItem) : Boolean {
//        val extra = FragmentNavigatorExtras(
//            brewery_logo to "brewery_logo",
//            brewery_name to "brewery_name"
//        )
        val action = BreweryFragmentDirections.actionBreweryFragmentToBreweryDetailFragment2(
            arrayOf(item.breweryName, item.breweryLabel))
        val extra = FragmentNavigator.Extras.Builder().addSharedElement(v.brewery_logo, ViewCompat.getTransitionName(v.brewery_logo)!!).build()
        findNavController().navigate(action, extra)
        return false
    }
}