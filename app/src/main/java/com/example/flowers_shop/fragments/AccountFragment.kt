package com.example.flowers_shop.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.flowers_shop.PreferenceManager
import com.example.flowers_shop.databinding.FragmentAccountBinding


class AccountFragment : Fragment() {

    private var _binding: FragmentAccountBinding? = null
    private val binding get() = _binding!!
    private val args: AccountFragmentArgs by navArgs()
    private var username = ""

//    val prefManager = PreferenceManager(requireContext())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAccountBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        username = args.username

        binding.username.text = username
    }

    override fun onDestroy() {
        super.onDestroy()

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}