package com.example.househuntingkt

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DashboardActivity : AppCompatActivity() {

    private lateinit var searchBar: EditText
    private lateinit var searchLogo: ImageView
    private lateinit var btnCity1: Button
    private lateinit var btnCity2: Button
    private lateinit var btnCity3: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard)

        // Initialize views
        searchBar = findViewById(R.id.searchBar)
        searchLogo = findViewById(R.id.searchlogo)
        btnCity1 = findViewById(R.id.btnCity1)
        btnCity2 = findViewById(R.id.btnCity2)
        btnCity3 = findViewById(R.id.btnCity3)

        // Search logo click listener
        searchLogo.setOnClickListener {
            val searchText = searchBar.text.toString()
            if (searchText.isNotEmpty()) {
                // Handle search action
                Toast.makeText(this, "Searching for $searchText", Toast.LENGTH_SHORT).show()
                // You can start a new activity for search results here
            } else {
                Toast.makeText(this, "Please enter a search term", Toast.LENGTH_SHORT).show()
            }
        }

        // City buttons click listeners
        btnCity1.setOnClickListener {
            handleCitySelection("Ahmedabad")
        }

        btnCity2.setOnClickListener {
            handleCitySelection("Mumbai")
        }

        btnCity3.setOnClickListener {
            handleCitySelection("Bengaluru")
        }
    }

    // Handle city selection
    private fun handleCitySelection(city: String) {
        Toast.makeText(this, "Selected: $city", Toast.LENGTH_SHORT).show()
        // Example: Start an activity based on city selection
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("city_name", city)
        startActivity(intent)
    }
}
