package com.example.househuntingkt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DashboardActivity: AppCompatActivity() {


        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.dashboard)

            val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
            recyclerView.layoutManager = LinearLayoutManager(this)

            val properties = listOf(
                Panvel.Property(
                    "Vitalia",
                    "Pinnacle AKS Real Estate",
                    "Dubai, Palm Jumeirah",
                    R.drawable.pg1
                ),
                Panvel.Property("Silver Park", "Manjothi Real Estate", "Dubai, International City", R.drawable.pg2)
                // Add more properties
            )

            recyclerView.adapter = Panvel(properties)
        }

}