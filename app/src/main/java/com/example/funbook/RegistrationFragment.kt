package com.example.funbook

import android.app.AlertDialog
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.funbook.databinding.FragmentRegistrationBinding


class RegistrationFragment :
    BaseFragment<FragmentRegistrationBinding>(FragmentRegistrationBinding::inflate) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.alreadyLogin.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.registerBtn.setOnClickListener {
            val name = binding.userName.text.toString().trim()
            val email = binding.userEmail.text.toString().trim()
            val password = binding.userPassword.text.toString().trim()

            if (name.isBlank()) {
                binding.userNameField.error = "Enter your name..."
                binding.userNameField.requestFocus()
            } else if (email.isBlank()) {
                binding.userEmailField.error = "Enter your email..."
                binding.userEmailField.requestFocus()
            }
            else if(email.isNotEmpty() && !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.userEmailField.error = "Put your valid email address"
                binding.userEmailField.requestFocus()
            }
            else if (password.isBlank()) {
                binding.passwordTextField.error = "Password can't be blank..."
                binding.passwordTextField.requestFocus()
            } else if (password.length < 6) {
                binding.passwordTextField.error = "Password should more than 6 or more"
                binding.passwordTextField.requestFocus()
            } else {
                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(requireContext(), "User registration is complete...", Toast.LENGTH_SHORT).show()
                        mAuth.signOut()
                        findNavController().popBackStack()
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