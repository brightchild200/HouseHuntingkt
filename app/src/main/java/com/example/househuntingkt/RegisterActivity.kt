package com.example.househuntingkt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.househuntingkt.R.id.password

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)

//        val backArrow = findViewById<ImageView>(R.id.backArrow)
        val getStarted = findViewById<Button>(R.id.getStarted)
        val login = findViewById<Button>(R.id.login)
        val name = findViewById<EditText>(R.id.name)
        val mobile = findViewById<EditText>(R.id.phone)
        val email = findViewById<EditText>(R.id.email)
        val password = findViewById<EditText>(password)
        val male = findViewById<Button>(R.id.male)
        val female = findViewById<Button>(R.id.female)
        val buyer = findViewById<Button>(R.id.buyer)
        val seller = findViewById<Button>(R.id.seller)
        // Back to MainActivity (Home)

        // Handle Get Started button click
        getStarted.setOnClickListener {
            val tname = name.text.toString().trim()
            val tmobile = mobile.text.toString().trim()
            val temail = email.text.toString().trim()
            val tpassword = password.text.toString().trim()

            if (tname.isEmpty() || tmobile.isEmpty() || temail.isEmpty() || tpassword.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            } else {
                // You can proceed with form submission logic here
                Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show()
            }
        }

        // Redirect to LoginActivity

        login.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        // Back arrow functionality to go to RegisterActivity
        getStarted.setOnClickListener {
            val go = Intent(this, DashboardActivity::class.java)
            startActivity(go)
        }

        buyer.setOnClickListener {
            val buyerdashboard = Intent(this, BuyerDashboardActivity::class.java)
            startActivity(buyerdashboard)
        }

//        // Delay of 3 seconds (3000ms)
//        Handler(Looper.getMainLooper()).postDelayed({
//            // Redirect to ActivityMain
//            val intent = Intent(this@RegisterActivity, DashboardActivity::class.java)
//            startActivity(intent)
//            finish() // To prevent returning to this activity
//        }, 5000)


    }
}
