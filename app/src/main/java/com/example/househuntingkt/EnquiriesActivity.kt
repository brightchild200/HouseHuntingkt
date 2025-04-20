package com.example.househuntingkt

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.TouchDelegate
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.google.firebase.firestore.FirebaseFirestore
import android.graphics.Rect

class EnquiriesActivity : AppCompatActivity() {

    private lateinit var linearLayout: LinearLayout
    private val db = FirebaseFirestore.getInstance()
    private lateinit var backButton: ImageView
    private lateinit var navBar: LinearLayout
    private lateinit var navEnquiries: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.seller_enquiries)

        // Initialize views
        linearLayout = findViewById(R.id.enquire)
        backButton = findViewById(R.id.backArrow)
        navBar = findViewById(R.id.sellerNavBar)
        navEnquiries = navBar.findViewById(R.id.nav_bookings) // Adjust ID based on seller_navbar.xml

        // Set up back button click listener and touch delegate
        backButton.setOnClickListener {
            onBackPressed() // Navigate back
        }
        backButton.post {
            val parent = backButton.parent as ViewGroup
            parent.post {
                val delegate = CustomTouchDelegate(Rect(0, 0, dpToPx(64), dpToPx(64)), backButton)
                parent.touchDelegate = delegate
            }
        }

        // Set up navigation bar click listeners
        navEnquiries.setOnClickListener {
            // Highlight current tab (optional visual feedback)
            navEnquiries.findViewById<TextView>(android.R.id.text1)?.setTextColor(getColor(android.R.color.black))
            Toast.makeText(this, "Enquiries Selected", Toast.LENGTH_SHORT).show()
        }

        // Initialize other navigation items (adjust IDs based on seller_navbar.xml)
        val navProperty = navBar.findViewById<LinearLayout>(R.id.nav_property)
        val navListings = navBar.findViewById<LinearLayout>(R.id.nav_listings)
        val navHome = navBar.findViewById<LinearLayout>(R.id.nav_home)
        val navProfile = navBar.findViewById<LinearLayout>(R.id.nav_profile)

        navProperty?.setOnClickListener {
            // Replace with actual activity or remove if not implemented
            Toast.makeText(this, "Property clicked (implement AddPropertyActivity)", Toast.LENGTH_SHORT).show()
        }

        navListings?.setOnClickListener {
            // Replace with actual activity or remove if not implemented
            Toast.makeText(this, "Listings clicked (implement ListingsActivity)", Toast.LENGTH_SHORT).show()
        }

        navHome?.setOnClickListener {
            // Replace with actual activity or remove if not implemented
            Toast.makeText(this, "Home clicked (implement HomeActivity)", Toast.LENGTH_SHORT).show()
        }

        navProfile?.setOnClickListener {
            // Replace with actual activity or remove if not implemented
            Toast.makeText(this, "Profile clicked (implement ProfileActivity)", Toast.LENGTH_SHORT).show()
        }

        fetchBuyersFromFirestore()
    }

    private fun fetchBuyersFromFirestore() {
        db.collection("users")
            .get()
            .addOnSuccessListener { result ->
                linearLayout.removeAllViews() // Clear existing views to avoid duplicates
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
        try {
            val inflater = LayoutInflater.from(this)
            val cardView = inflater.inflate(R.layout.buyer_card_layout, null) as CardView

            val nameText: TextView = cardView.findViewById(R.id.buyerName)
            val lookingForText: TextView = cardView.findViewById(R.id.lookingFor)
            val msgButton: ImageButton = cardView.findViewById(R.id.msgButton)
            val callButton: ImageButton = cardView.findViewById(R.id.callButton)
            val acceptButton: Button = cardView.findViewById(R.id.acceptButton)
            val rejectButton: Button = cardView.findViewById(R.id.rejectButton)
            val profileImage: ImageView = cardView.findViewById(R.id.profileImage)

            nameText.text = buyer.name
            lookingForText.text = "Looking for a ${buyer.gender} PG\nMobile: ${buyer.mobile}"

            // Set content descriptions for accessibility
            acceptButton.contentDescription = getString(R.string.accept)
            rejectButton.contentDescription = getString(R.string.reject)
            msgButton.contentDescription = getString(R.string.message_description)
            callButton.contentDescription = getString(R.string.call_description)

            linearLayout.addView(cardView)
        } catch (e: Exception) {
            Toast.makeText(this, "Error inflating buyer card: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun dpToPx(dp: Int): Int {
        return (dp * resources.displayMetrics.density).toInt()
    }
}

// Custom TouchDelegate class
class CustomTouchDelegate(bounds: Rect, targetView: View) : TouchDelegate(bounds, targetView) {
    override fun onTouchEvent(event: MotionEvent): Boolean {
        return super.onTouchEvent(event)
    }
}

//package com.example.househuntingkt
//
//import android.content.Intent
//import android.os.Bundle
//import android.text.Editable
//import android.text.TextWatcher
//import android.view.LayoutInflater
//import android.view.View
//import android.widget.*
//import androidx.appcompat.app.AppCompatActivity
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import com.google.firebase.firestore.FirebaseFirestore
//import androidx.cardview.widget.CardView
//
//class EnquiriesActivity : AppCompatActivity() {
//
//    private lateinit var linearLayout: LinearLayout
//    private val db = FirebaseFirestore.getInstance()
//    private lateinit var backButton: ImageView // Assuming backButton is an ImageView
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.seller_enquiries)
//
////        backButton = finAdViewById(R.id.list) // Back button ID should match your layout
//
//        linearLayout = findViewById(R.id.enquire)
//        // Find the LinearLayout where cards will be added
//
//        fetchBuyersFromFirestore()
//    }
//
//
//    private fun fetchBuyersFromFirestore() {
//        db.collection("users")
//            .get()
//            .addOnSuccessListener { result ->
//                for (document in result) {
//                    val buyer = document.toObject(Buyer::class.java)
//                    addBuyerCard(buyer)
//                }
//            }
//            .addOnFailureListener {
//                Toast.makeText(this, "Error fetching buyers", Toast.LENGTH_SHORT).show()
//            }
//    }
//
//    private fun addBuyerCard(buyer: Buyer) {
//        val inflater = LayoutInflater.from(this)
//        val cardView = inflater.inflate(R.layout.buyer_card_layout, null) as CardView // Inflate as CardView
//
//        val nameText: TextView = cardView.findViewById(R.id.buyerName)
//        val lookingForText: TextView = cardView.findViewById(R.id.lookingFor)
//        val msgButton: ImageButton = cardView.findViewById(R.id.msgButton)
//        val callButton: ImageButton = cardView.findViewById(R.id.callButton)
//        val acceptButton: Button = cardView.findViewById(R.id.acceptButton)
//        val rejectButton: Button = cardView.findViewById(R.id.rejectButton)
//        val profileImage: ImageView = cardView.findViewById(R.id.profileImage)
//
//        nameText.text = buyer.name
//        lookingForText.text = "Looking for a ${buyer.gender} PG\nMobile: ${buyer.mobile}"
//
//        // Set click listeners or any other dynamic properties here
//
//        linearLayout.addView(cardView)
//    }
//}
