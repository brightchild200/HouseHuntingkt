package com.example.househuntingkt

    import android.content.Intent
    import android.os.Bundle
    import android.widget.ImageView
    import androidx.appcompat.app.AppCompatActivity

    class Nerul : AppCompatActivity() {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.nerul)

            // Find ImageViews by ID
            val propertyImage1 = findViewById<ImageView>(R.id.nerulimg1)
            val propertyImage2 = findViewById<ImageView>(R.id.nerulimg2)
//            val propertyImage3 = findViewById<ImageView>(R.id.nerulim3)
//            val propertyImage4 = findViewById<ImageView>(R.id.property_image4)

            // Set click listeners for each image
            propertyImage1.setOnClickListener {
                val intent = Intent(this, Nerul1::class.java)
                startActivity(intent)
            }

            propertyImage2.setOnClickListener {
                val intent = Intent(this, Nerul1::class.java)
                startActivity(intent)
            }
//
//            propertyImage3.setOnClickListener {
//                val intent = Intent(this, StMarryHousingSocietyActivity::class.java)
//                startActivity(intent)
//            }
//
//            propertyImage4.setOnClickListener {
//                val intent = Intent(this, SwamiSadanCHSActivity::class.java)
//                startActivity(intent)
//            }
        }
    }
