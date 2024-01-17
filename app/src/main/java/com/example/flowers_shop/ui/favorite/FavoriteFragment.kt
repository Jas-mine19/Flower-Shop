package com.example.flowers_shop.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.flowers_shop.ui.FavoriteAdapter
import com.example.flowers_shop.databinding.FragmentFavoriteBinding
import com.example.flowers_shop.ui.HomeViewModel

class FavoriteFragment : Fragment() {
    private var _binding: FragmentFavoriteBinding? = null

    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    private lateinit var favoriteAdapter: FavoriteAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpAdapter()
        setUpObserve()
        getFlower()

        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }

    }


    private fun setUpObserve() {
        viewModel.flowerLiveData.observe(viewLifecycleOwner) {
            favoriteAdapter.submitList(it)
        }
    }

    private fun setUpAdapter() {
        favoriteAdapter = FavoriteAdapter()
        binding.favoriteRv.adapter = favoriteAdapter
        favoriteAdapter.onItemClick = {
            Toast.makeText(requireContext(), "Adding ${it}to the basket", Toast.LENGTH_LONG)
        }
    }

    private fun getFlower() {
        viewModel.getFlowerList()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}