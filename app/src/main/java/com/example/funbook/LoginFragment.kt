package com.example.funbook

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.funbook.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth


class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val currentUser = FirebaseAuth.getInstance().currentUser

        if (currentUser != null) {
            findNavController().navigate(R.id.action_loginFragment_to_dashboard)

        }
        return super.onCreateView(inflater, container, savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.registerBtn.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
        }
        binding.loginBtn.setOnClickListener {
            val email = binding.userEmail.text.toString().trim()
            val password = binding.userPassword.text.toString().trim()

            if (email.isBlank()) {
                binding.userEmailField.error = "Enter your email..."
                binding.userEmailField.requestFocus()
            }
            else if (password.isBlank()) {
                binding.passwordTextField.error = "Password can't be blank..."
                binding.passwordTextField.requestFocus()
            }
            else {
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(
                            requireContext(),
                            "User Logged in...",
                            Toast.LENGTH_SHORT
                        ).show()

                        binding.userEmail.text = null
                        binding.userPassword.text = null
                        findNavController().navigate(R.id.action_loginFragment_to_dashboard)
                    }
                }
                    .addOnFailureListener {
                        val dialog = AlertDialog.Builder(requireContext())
                        dialog.setTitle("Failure Error...")
                        dialog.setMessage(it.message)
                        dialog.create().show()
                    }
            }
        }
    }
}