package com.example.househuntingkt

import com.google.firebase.firestore.SetOptions
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class RoleSelectiionActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.roleselection)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        val buyerButton = findViewById<Button>(R.id.buyer)
        val sellerButton = findViewById<Button>(R.id.seller)

        val userId = auth.currentUser?.uid

        buyerButton.setOnClickListener {
            userId?.let {
                // Set the role to Buyer
                db.collection("users").document(it)
                    .update("role", "buyer")
                    .addOnSuccessListener {
                        val intent = Intent(this, DashboardActivity::class.java)
                        intent.putExtra("role", "buyer") // ✅ Pass the role here!
                        startActivity(intent)
                        finish()
                    }
            }
        }

        sellerButton.setOnClickListener {
            userId?.let { uid ->
                db.collection("users").document(uid)
                    .set(mapOf("role" to "buyer"), SetOptions.merge())
                    .addOnSuccessListener {
                        val intent = Intent(this, DashboardActivity::class.java)
                        intent.putExtra("role", "seller")
                        startActivity(intent)
                        finish()
                    }
            }
        }
    }
}
