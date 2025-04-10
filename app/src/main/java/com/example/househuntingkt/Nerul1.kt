package com.example.househuntingkt

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class Nerul1 : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.nerul1)  // Ensure this layout file exists


        // Back button to navigate to Nerul page
        val backButton: ImageView = findViewById(R.id.backArrow)
        backButton.setOnClickListener {
            val intent = Intent(this, Nerul::class.java)
            startActivity(intent)
            finish()
        }

        // Call Now button
        val callButton: Button = findViewById(R.id.call)
        callButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:8655473211") // Replace with the actual phone number
            startActivity(intent)
        }

        // Check Availability button
        val checkAvailabilityButton: Button = findViewById(R.id.rent)
        checkAvailabilityButton.setOnClickListener {
            Toast.makeText(this, "Checking availability...", Toast.LENGTH_SHORT).show()
            // Implement the actual availability checking logic here
        }
    }



}
