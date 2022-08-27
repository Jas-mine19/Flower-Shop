package com.example.flowers_shop.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.flowers_shop.adapters.BasketAdapter
import com.example.flowers_shop.adapters.CategoryAdapter
import com.example.flowers_shop.databinding.FragmentBasketBinding
import com.example.flowers_shop.mvvm.BasketViewModel
import com.example.flowers_shop.mvvm.HomeViewModel

class BasketFragment : Fragment() {


    private var _binding: FragmentBasketBinding? = null
    private val binding get() = _binding!!

    private val viewModel: BasketViewModel by viewModels()

    private lateinit var basketAdapter: BasketAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentBasketBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setOnClickListener()
        setUpObserves()
        setUpAdapter()
        getBasket()


    }

    private fun setUpAdapter() {
        basketAdapter = BasketAdapter()
        binding.basketRv.adapter = basketAdapter
    }

    private fun setUpObserves() {
        viewModel.basketLiveData.observe(viewLifecycleOwner) {
            basketAdapter.submitList(it)
        }
    }

    private fun getBasket() {
        viewModel.getBasketList()
    }

    private fun setOnClickListener() {
        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
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