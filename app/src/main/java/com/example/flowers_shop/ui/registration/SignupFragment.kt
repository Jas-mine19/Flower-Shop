package com.example.flowers_shop.ui.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.flowers_shop.common.preferences.PreferenceManager
import com.example.flowers_shop.R
import com.example.flowers_shop.models.User
import com.example.flowers_shop.databinding.FragmentSignupBinding
import com.example.flowers_shop.fragments.SignupFragmentDirections

class SignupFragment : Fragment() {


    private var _binding: FragmentSignupBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: SignupViewModel
    private lateinit var prefManager: PreferenceManager



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[SignupViewModel::class.java]

        setOnClickListener()

    }


    private fun setOnClickListener() {
        binding.signInText.setOnClickListener {
            findNavController().navigate(R.id.action_signupFragment_to_loginFragment)

        }

        binding.createAccountButton.setOnClickListener {
            val login = binding.email.text.toString()
            val username = binding.username.text.toString()
            val password = binding.password.text.toString()
            val password2 = binding.password2.text.toString()

            val direction = SignupFragmentDirections.actionSignupFragmentToAccountFragment(
                username,
                login,
                password
            )
            findNavController().navigate(direction)

            if (login.isNotEmpty() && password.isNotEmpty() && username.isNotEmpty() && password2.isNotEmpty()) {
                if (password == password2) {
                    viewModel.signupLiveData.observe(viewLifecycleOwner) {
                        if (it) {
                            findNavController().navigate(R.id.action_signupFragment_to_mainFragment2)
                        }
                        if (it){
                            prefManager.setLogin(true)
                        }
                    }
                    viewModel.errorSignupLiveData.observe(viewLifecycleOwner) {
                        Toast.makeText(
                            requireContext(),
                            it,
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                    viewModel.createUser(
                        User(
                            0,
                            username,
                            login,
                            password
                        )
                    )

                } else {
                    Toast.makeText(
                        requireContext(),
                        "Password is not matching",
                        Toast.LENGTH_SHORT
                    )
                        .show()
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