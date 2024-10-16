package com.example.househuntingkt

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Nerul1 : AppCompatActivity() {

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.nerul1)

        val backButton: Button = findViewById(R.id.backArrow)
        backButton.setOnClickListener {
            val intent = Intent(this, Nerul::class.java)
            startActivity(intent)
            finish()
        }

        // Call Now Button
        val callButton: Button = findViewById(R.id.call)
        callButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:9XXXXXXXXX") // Replace with the actual phone number
            startActivity(intent)
        }

        // Check Availability Button
        val checkAvailabilityButton: Button = findViewById(R.id.rent)
        checkAvailabilityButton.setOnClickListener {
            Toast.makeText(this, "Checking availability...", Toast.LENGTH_SHORT).show()
            // Implement your logic for checking availability
        }
    }
}
