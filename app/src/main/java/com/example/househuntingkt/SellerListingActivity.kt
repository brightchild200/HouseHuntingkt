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
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private val propertyList = mutableListOf<Property>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.listing)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        propertyAdapter = PropertyAdapter(propertyList, this, ::deleteProperty)
        recyclerView.adapter = propertyAdapter

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        val currentUserId = auth.currentUser?.uid

        val backArrow = findViewById<ImageView>(R.id.backArrow)
        backArrow.setOnClickListener { finish() }

        if (currentUserId != null) {
            db.collection("properties")
                .whereEqualTo("sellerId", currentUserId)
                .get()
                .addOnSuccessListener { documents ->
                    propertyList.clear()
                    for (doc in documents) {
                        val property = doc.toObject(Property::class.java)
                        propertyList.add(property)
                    }
                    propertyAdapter.notifyDataSetChanged()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Failed to load listings: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        }
    }

    private fun deleteProperty(property: Property) {
        db.collection("properties")
            .whereEqualTo("sellerId", auth.currentUser?.uid)
            .whereEqualTo("title", property.title)
            .get()
            .addOnSuccessListener { documents ->
                for (doc in documents) {
                    db.collection("properties").document(doc.id)
                        .delete()
                        .addOnSuccessListener {
                            Toast.makeText(this, "Property deleted", Toast.LENGTH_SHORT).show()
                            propertyList.remove(property)
                            propertyAdapter.notifyDataSetChanged()
                        }
                        .addOnFailureListener {
                            Toast.makeText(this, "Error deleting property", Toast.LENGTH_SHORT).show()
                        }
                }
            }
    }
}
