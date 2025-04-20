package com.example.househuntingkt

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

//data class WishlistItem(
//    val location: String,
//    val owner: String,
//    val status: String,
//    val imageResId: Int
//)

class WishlistActivity : AppCompatActivity() {

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.wishlist)
//
//        // Back Arrow Click Listener
//        findViewById<ImageView>(R.id.backArrow).setOnClickListener {
//            onBackPressed()
//        }
//
//        // Get the LinearLayout inside ScrollView
//        val wishlistContainer = findViewById<LinearLayout>(R.id.wishlistLinearLayout)
//
//        // Mock wishlist data (replace with actual data source)
//        val wishlistItems = listOf(
//            WishlistItem("Nerul, Navi Mumbai", "Owner: Ram Sharma", "Status: Available in 1 month", R.drawable.img),
//            WishlistItem("Sector 15, Navi Mumbai", "Owner: Anil Kumar", "Status: Available in 2 months", R.drawable.img2),
//            WishlistItem("Palm Beach Road, Navi Mumbai", "Owner: Sunita Patel", "Status: Available in 3 months", R.drawable.img),
//            WishlistItem("Vashi, Navi Mumbai", "Owner: Vikram Singh", "Status: Available in 1 month", R.drawable.img2)
//        )
//
//        // Dynamically add CardViews
//        wishlistItems.forEach { item ->
//            val cardView = LayoutInflater.from(this).inflate(R.layout.wishlist_card, wishlistContainer, false) as CardView
//            val linearLayout = cardView.findViewById<LinearLayout>(android.R.id.content)
//            val houseImage = linearLayout.findViewById<ImageView>(R.id.houseImage)
//            val locationText = linearLayout.findViewById<TextView>(R.id.locationText)
//            val ownerText = linearLayout.findViewById<TextView>(R.id.ownerText)
//            val statusText = linearLayout.findViewById<TextView>(R.id.statusText)
//
//            houseImage.setImageResource(item.imageResId)
//            locationText.text = item.location
//            ownerText.text = item.owner
//            statusText.text = item.status
//
//            wishlistContainer.addView(cardView)
//        }
//    }
}