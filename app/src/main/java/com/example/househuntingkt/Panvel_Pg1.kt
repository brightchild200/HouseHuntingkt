package com.example.househuntingkt

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Panvel_Pg1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.panvel_pg1)

        // Call Now Button
        val callButton: Button = findViewById(R.id.panvelpg1call)
        callButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:9XXXXXXXXX") // Replace with the actual phone number
            startActivity(intent)
        }

        // Check Availability Button
        val checkAvailabilityButton: Button = findViewById(R.id.panvelpg1btn)
        checkAvailabilityButton.setOnClickListener {
            Toast.makeText(this, "Checking availability...", Toast.LENGTH_SHORT).show()
            // Implement your logic for checking availability
        }
    }
}
