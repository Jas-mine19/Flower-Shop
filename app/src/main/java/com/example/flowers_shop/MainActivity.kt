package com.example.flowers_shop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.flowers_shop.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var binding_: ActivityMainBinding? = null
    private val binding get() = binding_!!



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding_ = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)





    }
}