package com.example.flowers_shop.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.flowers_shop.R
import com.example.flowers_shop.databinding.FragmentSignupBinding
import com.google.firebase.auth.FirebaseAuth

class SignupFragment : Fragment() {


    private var _binding: FragmentSignupBinding? = null
    private val binding get() = _binding!!

    private lateinit var firebaseAuth: FirebaseAuth


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
        //firebaseAuth = FirebaseAuth.getInstance().createUserWithEmailAndPassword()
        binding.signInText.setOnClickListener {
            findNavController().navigate(R.id.action_signupFragment_to_loginFragment)
        }

        binding.createAccountButton.setOnClickListener {
            val email = binding.email.text.toString()
            val username = binding.username.text.toString()
            val password = binding.password.text.toString()
            val password2 = binding.password2.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty() && username.isNotEmpty() && password2.isNotEmpty()) {
                if (password == password2) {

                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener() {
                            if (it.isSuccessful) {
                               findNavController().navigate(R.id.action_signupFragment_to_firstCoverFragment)
                            } else {
                                Toast.makeText(
                                    requireContext(),
                                    it.exception.toString(),
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                            }
                        }
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