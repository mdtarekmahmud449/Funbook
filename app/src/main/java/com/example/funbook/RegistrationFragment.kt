package com.example.funbook

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.funbook.databinding.FragmentRegistrationBinding

class RegistrationFragment : BaseFragment<FragmentRegistrationBinding> (FragmentRegistrationBinding::inflate) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.alreadyLogin.setOnClickListener{
            findNavController().popBackStack()
        }
    }
}