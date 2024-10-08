package com.example.lab_1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class ClubAdapter(private val onClubClick: (Clubs) -> Unit) : ListAdapter<Clubs, ClubAdapter.ViewHolder>(ClubDiffUtil()) {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var clubName: TextView = itemView.findViewById(R.id.tv_club_name)
        var clubImage: ImageView = itemView.findViewById(R.id.product_image)
        val constraintLayout: ConstraintLayout = itemView.findViewById(R.id.product_row)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater: LayoutInflater = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.product_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentClub = getItem(position)
        holder.clubImage.setImageResource(currentClub.img)
        holder.clubName.text = currentClub.name
        holder.itemView.setOnClickListener {
            onClubClick(currentClub)
        }
    }
}

