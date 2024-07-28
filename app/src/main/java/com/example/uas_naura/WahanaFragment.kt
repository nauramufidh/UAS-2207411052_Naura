package com.example.uas_naura

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class WahanaFragment : Fragment() {

    private lateinit var wahanaRecyclerView: RecyclerView
    private lateinit var wahanaAdapter: WahanaAdapter
    private lateinit var wahanaList: List<Wahana>
    private lateinit var dbHelper: WahanaDatabaseHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_wahana, container, false)

        wahanaRecyclerView = view.findViewById(R.id.wahana_list)
        wahanaRecyclerView.layoutManager = LinearLayoutManager(context)
        wahanaRecyclerView.setHasFixedSize(true)

        dbHelper = WahanaDatabaseHelper(requireContext())

        // Gunakan metode getAllWahana untuk mendapatkan data dari database
        wahanaList = dbHelper.getAllWahana()

        wahanaAdapter = WahanaAdapter(wahanaList)
        wahanaRecyclerView.adapter = wahanaAdapter

        return view
    }
}
