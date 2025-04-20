package com.example.househuntingkt

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
//import com.google.android.gms.maps.CameraUpdateFactory
//import com.google.android.gms.maps.GoogleMap
//import com.google.android.gms.maps.OnMapReadyCallback
//import com.google.android.gms.maps.SupportMapFragment
//import com.google.android.gms.maps.model.LatLng
//import com.google.android.gms.maps.model.MarkerOptions

class Vashi1 : AppCompatActivity() {

//    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.vashi1)

//        // **📌 Initialize Map Fragment**
//        val mapFragment = supportFragmentManager.findFragmentById(R.id.mapContainer) as SupportMapFragment
//        mapFragment.getMapAsync(this)



        // **⬅️ Back Button**
        val backButton: ImageView = findViewById(R.id.backArrow)
        backButton.setOnClickListener {
            val intent = Intent(this, Vashi::class.java)
            startActivity(intent)
            finish()
        }

        // **📞 Call Now Button**
        val callButton: Button = findViewById(R.id.call)
        callButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:8456023012")
            startActivity(intent)
        }

        // **✅ Check Availability Button**
        val checkAvailabilityButton: Button = findViewById(R.id.rent)
        checkAvailabilityButton.setOnClickListener {
            Toast.makeText(this, "Checking availability...", Toast.LENGTH_SHORT).show()
        }
    }

//    override fun onMapReady(googleMap: GoogleMap) {
//        mMap = googleMap
//
//        // **📍 Vashi1 House Location**
//        val houseLocation = LatLng(19.077064, 72.998993) // Replace with actual lat/lng
//        mMap.addMarker(MarkerOptions().position(houseLocation).title("West End, Near Inorbit Mall, Vashi"))
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(houseLocation, 15f))
//    }
}
