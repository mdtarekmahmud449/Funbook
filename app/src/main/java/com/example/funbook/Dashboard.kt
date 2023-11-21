package com.example.funbook

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.funbook.databinding.FragmentDashboardBinding

class Dashboard : BaseFragment<FragmentDashboardBinding>(FragmentDashboardBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signOut.setOnClickListener {
            mAuth.signOut()
            Toast.makeText(
                requireContext(),
                "User sign out...",
                Toast.LENGTH_SHORT
            ).show()
            findNavController().popBackStack()
        }
    }
}