package com.example.flowers_shop.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.flowers_shop.PreferenceManager
import com.example.flowers_shop.R
import com.example.flowers_shop.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {


    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!


    private lateinit var prefManager: PreferenceManager
    private lateinit var edUsername: EditText
    private lateinit var edPassword: EditText
    private lateinit var username: String
    private lateinit var password: String
    private var name = ""


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
        binding.clickHereText.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signupFragment)
        }
        binding.loginButton.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_firstCoverFragment)
        }

    }

    private fun init() {
        prefManager = PreferenceManager(requireContext())

    }


    fun clickLogin(view: View) {
        username = binding.login.text.toString().trim()
        password = binding.password.text.toString().trim()

        if (username.isEmpty() || username == "") {
            edUsername.error = "Field is empty"
            edUsername.requestFocus()
        } else if (password.isEmpty() || password == "") {
            edPassword.error = "Field is empty"
            edPassword.requestFocus()
        } else {
            prefManager.setLogin(true)
            prefManager.setUsername(username)
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