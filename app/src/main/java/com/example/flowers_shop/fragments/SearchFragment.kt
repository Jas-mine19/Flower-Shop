package com.example.flowers_shop.fragments

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.flowers_shop.R
import com.example.flowers_shop.adapters.SearchingAdapter
import com.example.flowers_shop.databinding.FragmentSearchBinding
import com.example.flowers_shop.mvvm.SearchingViewModel


class SearchFragment : Fragment(), SearchView.OnQueryTextListener {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

   // private late init var flowerAdapter: FlowerAdapter
    private lateinit var searchingAdapter: SearchingAdapter

   // private var flowerList = listOf<Flowers>()

    // private val viewModel: FlowerReposViewModel by viewModels()
    private val viewModelSearching: SearchingViewModel by viewModels()

    private var searchRequest = ""


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root

    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onClickListener()
        initObservers()
        setupAdapter()
        getFlower()
        search()

        binding.searchFlower.isIconified = false
        binding.searchFlower.setIconifiedByDefault(true)

        binding.searchFlower.requestFocus()
        binding.searchFlower.onActionViewExpanded()

    }

    private fun onClickListener() {
        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }


    @RequiresApi(Build.VERSION_CODES.M)
    private fun search() {

        binding.searchFlower.setOnQueryTextListener(this)

        binding.searchFlower.apply {
            findViewById<TextView>(androidx.appcompat.R.id.search_src_text)
                .setHintTextColor(requireContext().getColor(R.color.black))
            findViewById<TextView>(androidx.appcompat.R.id.search_src_text)
                .setTextColor(Color.BLACK)
            findViewById<ImageView>(androidx.appcompat.R.id.search_close_btn)
                .setColorFilter(Color.BLACK)
        }
    }

    private fun setupAdapter() {
        searchingAdapter = SearchingAdapter()
        searchingAdapter.onItemClick = {
            val action = SearchFragmentDirections.actionSearchFragment2ToFlowerItemFragment(
                it.flowerName,
                it.flowerPrice,
                it.flowerInformation,
                it.imageURL.toString()
            )
            findNavController().navigate(action)
        }
        binding.searchRv.adapter = searchingAdapter
    }


    private fun initObservers() {
        Log.d("filter3", "initObservers: ")
        viewModelSearching.flowerLiveData.observe(viewLifecycleOwner) {
            searchingAdapter.setData(it)
            Log.d("filter4", "initObservers: ")
        }
    }


    private fun getFlower() {
        viewModelSearching.getFlowerList()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText != null) {
            searchingAdapter.getFilter()
            searchRequest = newText
        }
        return true
    }


}
