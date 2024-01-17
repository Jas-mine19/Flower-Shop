package com.example.flowers_shop.ui.basket

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.flowers_shop.CheckoutActivity
import com.example.flowers_shop.databinding.FragmentBasketBinding
import com.example.flowers_shop.ui.HomeViewModel

class BasketFragment : Fragment() {


    private var _binding: FragmentBasketBinding? = null
    private val binding get() = _binding!!


    //    private val viewModel: BasketViewModel by viewModels()
    private val viewModel: HomeViewModel by viewModels()

    private lateinit var basketAdapter: BasketAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentBasketBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // (activity as MainActivity).hideBottomNav()
        setOnClickListener()
        getFlower()
        setUpAdapter()
        setUpObserve()


    }

    private fun setUpObserve() {
        viewModel.flowerLiveData.observe(viewLifecycleOwner) {
            basketAdapter.submitList(it)
        }
    }

    private fun setUpAdapter() {
        basketAdapter = BasketAdapter()
        binding.basketRv.adapter = basketAdapter
    }

    private fun getFlower() {
        viewModel.getFlowerList()
    }

    private fun setOnClickListener() {

        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.btnBuy.visibility = View.VISIBLE
        binding.btnBuy.setOnClickListener {
            val intent = Intent(requireActivity(), CheckoutActivity::class.java)
            startActivity(intent)

        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}