package com.example.funbook

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.funbook.databinding.FragmentLoginBinding


class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.registerBtn.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
        }
    }
}