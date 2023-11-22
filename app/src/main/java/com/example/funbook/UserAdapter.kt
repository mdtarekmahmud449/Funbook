package com.example.funbook

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.funbook.databinding.UserSampleViewBinding

class UserAdapter(val userList: List<User>) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    class UserViewHolder(val binding: UserSampleViewBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = UserSampleViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val singleUser = userList[position]
        holder.binding.name.text = "Name: " +singleUser.name
        holder.binding.email.text = "Email: " + singleUser.email
        holder.binding.userId.text = "User Id: " + singleUser.userId
    }
}