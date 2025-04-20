package com.example.househuntingkt

import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class SellerListingActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var propertyAdapter: PropertyAdapter
    private lateinit var propertyList: MutableList<Property>
    private lateinit var db: FirebaseFirestore
    private lateinit var backArrow: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.listing)

        // Initialize Firebase and data list
        db = FirebaseFirestore.getInstance()
        propertyList = mutableListOf()

        // RecyclerView setup
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Correct parameter order for PropertyAdapter
        propertyAdapter = PropertyAdapter(
            propertyList = propertyList,  // List of properties to display
            context = this,  // Activity context
            isSellerView = true  // True for seller view, false for buyer view
        )
        recyclerView.adapter = propertyAdapter

        // Back arrow logic
        backArrow = findViewById(R.id.backArrow)
        backArrow.setOnClickListener {
            finish() // go back to the previous screen
        }

        // Fetch properties for seller
        fetchSellerProperties()
    }

    private fun fetchSellerProperties() {
        val currentUser = FirebaseAuth.getInstance().currentUser
        val sellerEmail = currentUser?.email

        if (sellerEmail == null) {
            Toast.makeText(this, "User not logged in", Toast.LENGTH_SHORT).show()
            return
        }

        db.collection("PGs")
            .whereEqualTo("email", sellerEmail)
            .get()
            .addOnSuccessListener { result ->
                propertyList.clear()
                for (document in result) {
                    val name = document.getString("name") ?: ""
                    val location = document.getString("location") ?: ""
                    val price = document.getString("price") ?: ""
                    val description = document.getString("description") ?: ""
                    val email = document.getString("email") ?: ""
                    val mobile = document.getString("whatsapp") ?: ""
                    val images = document.get("images") as? ArrayList<String> ?: arrayListOf()
                    val imageUrl = if (images.isNotEmpty()) images[0] else ""

                    propertyList.add(
                        Property(
                            imageUrl,
                            name,
                            location,
                            description,
                            price,
                            email,
                            mobile
                        )
                    )
                }
                propertyAdapter.notifyDataSetChanged()  // Notify the adapter to update the UI
            }
            .addOnFailureListener {
                Toast.makeText(this, "Failed to fetch properties", Toast.LENGTH_SHORT).show()
            }
    }
}
