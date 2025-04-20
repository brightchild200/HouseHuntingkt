package com.example.househuntingkt
// ← fix this based on your actual folder structure!

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.househuntingkt.R               // ← ensure correct R import!

class SellerProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seller_profile)

        val backButton = findViewById<ImageView>(R.id.backArrow)
        backButton.setOnClickListener {
            finish() // 👈 Closes SellerProfileActivity and returns to ListingsActivity
        }
    }
}
