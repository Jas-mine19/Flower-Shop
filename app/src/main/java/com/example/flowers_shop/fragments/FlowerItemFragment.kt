package com.example.flowers_shop.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.flowers_shop.R
import com.example.flowers_shop.databinding.FragmentFlowerItemBinding

class FlowerItemFragment : Fragment() {


    private var _binding: FragmentFlowerItemBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFlowerItemBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var number = 1
        binding.plusButton.setOnClickListener {
            if (number >= 0) {
                number += 1
                binding.number.setText(number.toString())
            }

        }

        binding.minusButton.setOnClickListener {
            if (number >= 1) {
                number -= 1
                binding.number.setText(number.toString())
            } else {
                onStop()
            }
        }

        binding.favoriteButton.setOnClickListener {
            binding.favoriteButton.setImageResource(R.drawable.icon_favorite_selected)
        }

        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.buy.setOnClickListener{
            //Toast.makeText(this,"Add to basket",Toast.LENGTH_SHORT)
        }


    }


    override fun onDestroy() {
        super.onDestroy()

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}