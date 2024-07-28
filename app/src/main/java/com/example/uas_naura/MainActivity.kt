package com.example.uas_naura

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.uas_naura.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var username: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Retrieve the username from the intent
        username = intent.getStringExtra("username")

        // Pass the username to HomeFragment initially
        replaceFragment(HomeFragment(), username)

        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> replaceFragment(HomeFragment(), username)
                R.id.wahana -> replaceFragment(WahanaFragment(), username)
                R.id.orders -> replaceFragment(OrdersFragment(), username)
                R.id.profile -> replaceFragment(ProfileFragment(), username)
                else -> {}
            }
            true
        }

        // Initialize the database and add sample data
        val dbHelper = WahanaDatabaseHelper(this)

        // Add sample wahana data to the database
        dbHelper.addWahana("Kora Kora", "Kapasitas Max : 30 Orang", "Wahana Ekstrem", R.drawable.zkorakora)
        dbHelper.addWahana("Sky Swinger", "Kapasitas Max : 25 Orang", "Wahana Ekstrem", R.drawable.zskyswinger)
        dbHelper.addWahana("Niagara Gara", "Kapasitas Max : 3 Orang", "Wahana Air", R.drawable.zniagaragara)
        dbHelper.addWahana("Roller Coaster", "Kapasitas Max : 24 Orang", "Wahana Ekstrem", R.drawable.zrollercoaster)
        dbHelper.addWahana("Arung Jeram", "Kapasitas Max : 8 Orang", "Wahana Air", R.drawable.zarungjeram)
        dbHelper.addWahana("Kicir-Kicir", "Kapasitas Max : 30 Orang", "Wahana Ekstrem", R.drawable.zkicirkicir)
        dbHelper.addWahana("Hysteria", "Kapasitas Max : 12 Orang", "Wahana Ekstrem", R.drawable.zhysteria)
        dbHelper.addWahana("Turbo Drop", "Kapasitas Max : 8 Orang", "Wahana Ekstrem", R.drawable.zturbodrop)
        dbHelper.addWahana("Paralayang", "Kapasitas Max : 14 Orang", "Wahana Ekstrem", R.drawable.zparalayang)
    }

    private fun replaceFragment(fragment: Fragment, username: String?) {
        val bundle = Bundle()
        bundle.putString("username", username)
        fragment.arguments = bundle

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }
}
