package com.example.flowers_shop.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.flowers_shop.databinding.FragmentFirstCoverBinding
import com.example.flowers_shop.fragments.FirstCoverFragmentDirections


class FirstCoverFragment : Fragment() {

    private var _binding: FragmentFirstCoverBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstCoverBinding.inflate(inflater, container, false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            startButton.setOnClickListener {
                val direction =
                    FirstCoverFragmentDirections.actionFirstCoverFragmentToMainFragment2()
                findNavController().navigate(direction)
            }

        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}