package com.example.househuntingkt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
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

        // Back to MainActivity (Home)


        // Handle Get Started button click
        getStarted.setOnClickListener {
            val name = name.text.toString().trim()
            val mobile = mobile.text.toString().trim()
            val email = email.text.toString().trim()
            val password = password.text.toString().trim()

            if (name.isEmpty() || mobile.isEmpty() || email.isEmpty() || password.isEmpty()) {
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

        // Delay of 3 seconds (3000ms)
        Handler(Looper.getMainLooper()).postDelayed({
            // Redirect to ActivityMain
            val intent = Intent(this@RegisterActivity, DashboardActivity::class.java)
            startActivity(intent)
            finish() // To prevent returning to this activity
        }, 3000)
    }
}
