package com.example.househuntingkt

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import androidx.cardview.widget.CardView

class EnquiriesActivity : AppCompatActivity() {

    private lateinit var linearLayout: LinearLayout
    private val db = FirebaseFirestore.getInstance()
    private lateinit var backButton: ImageView // Assuming backButton is an ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.seller_enquiries)

//        backButton = findViewById(R.id.list) // Back button ID should match your layout

        linearLayout = findViewById(R.id.enquire)
        // Find the LinearLayout where cards will be added

        fetchBuyersFromFirestore()
    }


    private fun fetchBuyersFromFirestore() {
        db.collection("users")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val buyer = document.toObject(Buyer::class.java)
                    addBuyerCard(buyer)
                }
            }
            .addOnFailureListener {
                Toast.makeText(this, "Error fetching buyers", Toast.LENGTH_SHORT).show()
            }
    }

    private fun addBuyerCard(buyer: Buyer) {
        val inflater = LayoutInflater.from(this)
        val cardView = inflater.inflate(R.layout.buyer_card_layout, null) as CardView // Inflate as CardView

        val nameText: TextView = cardView.findViewById(R.id.buyerName)
        val lookingForText: TextView = cardView.findViewById(R.id.lookingFor)
        val msgButton: ImageButton = cardView.findViewById(R.id.msgButton)
        val callButton: ImageButton = cardView.findViewById(R.id.callButton)
        val acceptButton: Button = cardView.findViewById(R.id.acceptButton)
        val rejectButton: Button = cardView.findViewById(R.id.rejectButton)
        val profileImage: ImageView = cardView.findViewById(R.id.profileImage)

        nameText.text = buyer.name
        lookingForText.text = "Looking for a ${buyer.gender} PG\nMobile: ${buyer.mobile}"

        // Set click listeners or any other dynamic properties here

        linearLayout.addView(cardView)
    }
}
