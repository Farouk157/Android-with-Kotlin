// FragmentA.kt
package com.example.lab_1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FragmentA : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var clubAdapter: ClubAdapter
    private lateinit var communicator: Communicator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_a, container, false)
        recyclerView = view.findViewById(R.id.product_list)

        communicator = activity as Communicator

        clubAdapter = ClubAdapter { club ->
            communicator.passDataToActivity2(club.details)
        }

        recyclerView.adapter = clubAdapter
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        clubAdapter.submitList(Clubs.RecyclerRepo.getClubs())

        return view
    }
}
