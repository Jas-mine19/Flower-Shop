package com.example.flowers_shop.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import com.example.flowers_shop.R
import com.example.flowers_shop.databinding.FragmentHomeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeFragment : Fragment() {


    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    //private val navController: NavController ?= null
    private lateinit var bottomNav: BottomNavigationView
    private lateinit var appBar: AppBarConfiguration
    private lateinit var navController: NavController


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
        bottomNav = binding.bottomNavigation
        bottomNav.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.favorite -> {
                    findNavController().navigate(R.id.action_homeFragment_to_favoriteFragment)
                }
                R.id.basket -> {
                    findNavController().navigate(R.id.action_homeFragment_to_basketFragment)
                }

            }
            true
        }

        binding.advertisingRv.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_advertisingFragment2)
        }

        binding.flowerRv.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_flowerItemFragment)
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