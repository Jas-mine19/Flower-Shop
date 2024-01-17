package com.example.flowers_shop.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.flowers_shop.R
import com.example.flowers_shop.models.Basket
import com.example.flowers_shop.databinding.FragmentFlowerItemBinding
import com.example.flowers_shop.fragments.FlowerItemFragmentArgs
import com.example.flowers_shop.ui.basket.BasketViewModel

class FlowerItemFragment : Fragment() {


    private var _binding: FragmentFlowerItemBinding? = null
    private val binding get() = _binding!!
    private var flowername = ""
    private var flowerprice = ""
    private var flowerinformation = ""
    private var flowerimage = ""
    private lateinit var viewModel: BasketViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFlowerItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[BasketViewModel::class.java]

        setOnClickListener()
        launchFlower()

    }

    private fun setOnClickListener() {
        var number = 1
        binding.addButton.setOnClickListener {
            if (number >= 0) {
                number += 1
                binding.number.text = number.toString()
                val price = flowerprice.toInt()
                val num = number * price
                binding.price.text = num.toString()
            }

        }

        binding.removeButton.setOnClickListener {
            if (number >= 1) {
                number -= 1
                binding.number.text = number.toString()
                val price = flowerprice.toInt()
                val num = (price * (number + 1)) - price
                binding.price.text = num.toString()
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

        binding.btnPrice.setOnClickListener {
            viewModel.basketLiveData.observe(viewLifecycleOwner) {
                if (it) {
                    Toast.makeText(requireActivity(), "Add to basket", Toast.LENGTH_SHORT).show()
                }
            }
            viewModel.errorBasketLiveData.observe(viewLifecycleOwner) {
                Toast.makeText(
                    requireContext(),
                    it,
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
            viewModel.createBasket(
                Basket(
                    "",
                    0.0,
                    0

                )
            )

        }


    }


    private fun launchFlower() {
        val args = FlowerItemFragmentArgs.fromBundle(requireArguments())
        flowername = args.flowername
        flowerprice = args.flowerprice
        flowerimage = args.flowerimage
        flowerinformation = args.flowerinformation
        binding.flowerName.text = flowername
        binding.flowerInformation.text = flowerinformation
        binding.price.text = flowerprice
        Glide
            .with(binding.image.context)
            .load(flowerimage)
            .into(binding.image)

    }


    override fun onDestroy() {
        super.onDestroy()

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}