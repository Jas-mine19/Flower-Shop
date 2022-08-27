package com.example.flowers_shop.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.flowers_shop.PreferenceManager
import com.example.flowers_shop.R
import com.example.flowers_shop.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment() {


    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private lateinit var firebaseAuth: FirebaseAuth



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
            findNavController().navigateUp()
        }
        binding.loginButton.setOnClickListener {
            val login = binding.login.text.toString()
            val password = binding.password.text.toString()

            if (login.isNotEmpty() && password.isNotEmpty() ) {

                FirebaseAuth.getInstance().signInWithEmailAndPassword(
                    login,
                    password
                )
                    .addOnCompleteListener() {
                        if (it.isSuccessful) {
                            findNavController().navigate(R.id.action_loginFragment_to_firstCoverFragment)
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