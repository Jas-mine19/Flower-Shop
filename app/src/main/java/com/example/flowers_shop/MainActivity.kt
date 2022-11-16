package com.example.flowers_shop


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.*
import androidx.navigation.fragment.NavHostFragment
import com.example.flowers_shop.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


@Suppress("DEPRECATED_IDENTITY_EQUALS")
class MainActivity : AppCompatActivity() {
    private var binding_: ActivityMainBinding? = null
    private val binding get() = binding_!!


    private lateinit var prefManager: PreferenceManager
    private lateinit var bottomNav: BottomNavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding_ = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        prefManager = PreferenceManager(this)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val inflater = navHostFragment.navController.navInflater
        val graph = inflater.inflate(R.navigation.nav_graph)




        if (!prefManager.isLogin() === true) {
            graph.setStartDestination(R.id.mainFragment2)
        } else {
            graph.setStartDestination(R.id.loginFragment)
        }
        navHostFragment.navController.graph = graph


    }
}


