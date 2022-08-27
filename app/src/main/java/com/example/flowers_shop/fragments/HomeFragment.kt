package com.example.flowers_shop.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.flowers_shop.R
import com.example.flowers_shop.adapters.AdvertisingAdapter
import com.example.flowers_shop.adapters.CategoryAdapter
import com.example.flowers_shop.adapters.FlowerAdapter
import com.example.flowers_shop.databinding.FragmentHomeBinding
import com.example.flowers_shop.mvvm.HomeViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var bottomNav: BottomNavigationView

    private lateinit var categoryAdapter: CategoryAdapter

    private lateinit var flowerAdapter: FlowerAdapter

    private lateinit var advertisingAdapter: AdvertisingAdapter

    private val viewModel: HomeViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpListeners()
        setUpObserves()
        setUpAdapters()
        getCategory()
        getAdvertising()
        getFlower()
        BottomNav()
    }

    private fun setUpObserves() {
        viewModel.categoryLiveData.observe(viewLifecycleOwner) {
            categoryAdapter.submitList(it)
        }

        viewModel.advertisingLiveData.observe(viewLifecycleOwner) {
            advertisingAdapter.submitList(it)
        }

        viewModel.flowerLiveData.observe(viewLifecycleOwner) {
            flowerAdapter.submitList(it)
        }
    }

    private fun setUpListeners() {
        binding.advertisingRv.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_advertisingFragment2)
        }

        binding.flowerRv.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_flowerItemFragment)
        }

        binding.profileImage.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_accountFragment)
        }
    }

    private fun setUpAdapters() {
        categoryAdapter = CategoryAdapter()
        binding.categoryRv.adapter = categoryAdapter
        advertisingAdapter = AdvertisingAdapter()
        binding.advertisingRv.adapter = advertisingAdapter
        flowerAdapter = FlowerAdapter()
        binding.flowerRv.adapter = flowerAdapter
    }

    private fun getCategory() {
        viewModel.getCategoryList()
    }

    private fun getAdvertising() {
        viewModel.getAdvertisingList()
    }

    private fun getFlower() {
        viewModel.getFlowerList()
    }

    private fun BottomNav() {
        bottomNav = binding.bottomNavigation
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.favorite -> {
                    findNavController().navigate(R.id.action_homeFragment_to_favoriteFragment)
                }
                R.id.basket -> {
                    findNavController().navigate(R.id.action_homeFragment_to_basketFragment)
                }

            }
            true
        }

    }

    override fun onResume() {
        super.onResume()
        bottomNav.selectedItemId = R.id.home
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}