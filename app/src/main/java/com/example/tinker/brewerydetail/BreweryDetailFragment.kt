package com.example.tinker.brewerydetail

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.api.load
import com.example.tinker.data.BreweryData
import com.example.tinker.data.BreweryDetailData
import com.example.tinker.databinding.BreweryDetailsFragmentBinding

class BreweryDetailFragment : Fragment(){

    private val args: BreweryDetailFragmentArgs by navArgs()
    private lateinit var binding: BreweryDetailsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BreweryDetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.breweryLogo.load(args.breweryDetails[1])
        binding.breweryName.text = args.breweryDetails[0]
    }
}