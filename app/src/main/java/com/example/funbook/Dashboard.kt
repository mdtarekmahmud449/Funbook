package com.example.funbook

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.funbook.databinding.FragmentDashboardBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class Dashboard : BaseFragment<FragmentDashboardBinding>(FragmentDashboardBinding::inflate) {
    private var userList: MutableList<User> = mutableListOf()
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

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        mRef.child("User").addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (sn in snapshot.children){
                    val user: User = sn.getValue(User::class.java)!!
                    userList.add(user)
                }
                binding.recyclerView.adapter = UserAdapter(userList)

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}