package com.example.flowers_shop.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.flowers_shop.R
import com.example.flowers_shop.databinding.FragmentLoginBinding
import com.example.flowers_shop.mvvm.LoginViewModel
import com.example.flowers_shop.mvvm.SignupViewModel
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment() {


    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: LoginViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        setOnClickListener()

    }


    private fun setOnClickListener() {
        binding.clickHereText.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.loginButton.setOnClickListener {
            val login = binding.login.text.toString()
            val password = binding.password.text.toString()

            if (login.isNotEmpty() && password.isNotEmpty()) {



                viewModel.loginLiveData.observe(viewLifecycleOwner) {
                    if (it) {
                        findNavController().navigate(R.id.action_loginFragment_to_firstCoverFragment)
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "email or password are wrong",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                }
            } else {
                Toast.makeText(
                    requireContext(),
                    "Empty fields",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
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