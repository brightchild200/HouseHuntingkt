package com.example.househuntingkt

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        auth = FirebaseAuth.getInstance()

        val emailEditText = findViewById<EditText>(R.id.email)
        val passwordEditText = findViewById<EditText>(R.id.password)
        val backArrow = findViewById<ImageView>(R.id.backArrow)
        val getStarted = findViewById<Button>(R.id.getStarted)
        val register = findViewById<Button>(R.id.reg)

        // Redirect to LoginActivity
        register.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        getStarted.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val intent = Intent(this, RoleSelectiionActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(this, "Login failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
            }
        }

        register.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}


//package com.example.househuntingkt
//
//import android.content.Intent
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.os.Handler
//import android.os.Looper
//import android.widget.Button
//import android.widget.EditText
//import android.widget.TextView
//import android.widget.Toast
//import com.example.househuntingkt.R.id.password
//
//class LoginActivity : AppCompatActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.login)
//
////        val backArrow = findViewById<ImageView>(R.id.backArrow)
//        val getStarted = findViewById<Button>(R.id.getStarted)
//        val register = findViewById<Button>(R.id.reg)
//
//        val email = findViewById<EditText>(R.id.email)
//        val password = findViewById<EditText>(password)
//
//        // Back to MainActivity (Home)
//
//
//        // Handle Get Started button click
//        getStarted.setOnClickListener {
//            val email = email.text.toString().trim()
//            val password = password.text.toString().trim()
//
//            if (email.isEmpty() || password.isEmpty()) {
//                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
//            } else {
//                // You can proceed with form submission logic here
//                Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show()
//            }
//        }
//
//        // Redirect to LoginActivity
//        register.setOnClickListener {
//            val intent = Intent(this, RegisterActivity::class.java)
//            startActivity(intent)
//        }
//
//
//        // Delay of 3 seconds (3000ms)
//        Handler(Looper.getMainLooper()).postDelayed({
//            // Redirect to ActivityMain
//            val intent = Intent(this@LoginActivity, DashboardActivity::class.java)
//            startActivity(intent)
//            finish() // To prevent returning to this activity
//        }, 5000)
//    }
//}
//
