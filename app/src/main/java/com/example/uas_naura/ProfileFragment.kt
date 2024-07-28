package com.example.uas_naura

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        val username = arguments?.getString("username")

        // Assuming you have a TextView to display the username in your profile section
        val profileUsernameTextView: TextView = view.findViewById(R.id.profileUsernameTextView)
        profileUsernameTextView.text = username

        return view
    }
}
