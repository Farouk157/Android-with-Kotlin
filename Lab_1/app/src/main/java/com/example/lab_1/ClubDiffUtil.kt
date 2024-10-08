package com.example.lab_1

import androidx.recyclerview.widget.DiffUtil

class ClubDiffUtil : DiffUtil.ItemCallback<Clubs>() {
    override fun areItemsTheSame(oldItem: Clubs, newItem: Clubs): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Clubs, newItem: Clubs): Boolean {
        return oldItem.name == newItem.name
    }
}