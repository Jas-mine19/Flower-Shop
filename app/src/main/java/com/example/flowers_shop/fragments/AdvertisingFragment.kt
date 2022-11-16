package com.example.flowers_shop.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.trusted.TrustedWebActivityDisplayMode.fromBundle
import androidx.browser.trusted.sharing.ShareData.fromBundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.flowers_shop.databinding.FragmentAdvertisingBinding
import com.example.flowers_shop.fragments.AdvertisingFragmentArgs.Companion.fromBundle

class AdvertisingFragment : Fragment() {


    private var _binding: FragmentAdvertisingBinding? = null

    private val binding get() = _binding!!
    var cardimage =""
    var discount = ""
    var information = ""


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAdvertisingBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }
        launchFlower()

    }

    private fun launchFlower() {
        val args = AdvertisingFragmentArgs.fromBundle(requireArguments())
        cardimage = args.cardimage
        discount = args.discount
        information = args.information
        binding.flowerInformation.text = information
        binding.discount.text = discount
        Glide
            .with(binding.imageView.context)
            .load(cardimage)
            .into(binding.imageView)
    }

    override fun onDestroy() {
        super.onDestroy()

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}