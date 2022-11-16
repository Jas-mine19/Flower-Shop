package com.example.flowers_shop.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.flowers_shop.PreferenceManager
import com.example.flowers_shop.R
import com.example.flowers_shop.databinding.FragmentAccountBinding


class AccountFragment : Fragment() {

    private var _binding: FragmentAccountBinding? = null
    private val binding get() = _binding!!
    private val args: AccountFragmentArgs by navArgs()
    private var username = ""
    private var login = ""
    private var password = ""

   // private val prefManager = PreferenceManager(requireContext())


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
        login = args.login
        password = args.password

        binding.username.text = username
        binding.logout.setOnClickListener {
         //   prefManager.removeData()
            findNavController().navigate(R.id.loginFragment)
            //childFragmentManager.popBackStack()

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