package com.example.flowers_shop.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.OnBackPressedDispatcherOwner
import androidx.activity.addCallback

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.flowers_shop.R
import com.example.flowers_shop.databinding.FragmentFirstCoverBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth


class FirstCoverFragment : Fragment() {


    private var _binding: FragmentFirstCoverBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstCoverBinding.inflate(inflater, container, false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
                requireActivity().finish()
            }
            startButton.setOnClickListener {
                val direction =
                    FirstCoverFragmentDirections.actionFirstCoverFragmentToHomeFragment()
                findNavController().navigate(direction)
            }

        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onPause() {
        super.onPause()
    }
}