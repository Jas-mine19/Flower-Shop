package com.example.flowers_shop.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.flowers_shop.R
import com.example.flowers_shop.databinding.FragmentMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var bottomNav: BottomNavigationView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val navHostFragment =
//            childFragmentManager.findFragmentById(R.id.main_nav_container) as NavHostFragment
//        var navController = navHostFragment.navController
//
//        val fragmentContainer = view.findViewById<View>(R.id.main_nav_container)
//        navController = Navigation.findNavController(fragmentContainer)

        BottomNav()


        //Setting the navigation controller to Bottom Nav
       // bottomNav.setupWithNavController(navController)


    }


    private fun BottomNav() {
        loadFragment(HomeFragment())
        bottomNav = binding.bottomNavigation
        bottomNav.setOnItemReselectedListener {
            when (it.itemId) {
                R.id.home -> {
                    loadFragment(HomeFragment())
                    return@setOnItemReselectedListener
                }

                R.id.favorite -> {
                    loadFragment(FavoriteFragment())
                    return@setOnItemReselectedListener
                }
                R.id.basket -> {
                    loadFragment(BasketFragment())
                    return@setOnItemReselectedListener
                }

            }
            true

        }

    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = childFragmentManager.beginTransaction()
        transaction.replace(R.id.main_nav_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}