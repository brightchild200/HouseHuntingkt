package com.example.househuntingkt

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.example.househuntingkt.R.id.password

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        val backArrow = findViewById<ImageView>(R.id.backArrow)
        val getStarted = findViewById<Button>(R.id.getStarted)
        val login = findViewById<Button>(R.id.login)
        val name = findViewById<EditText>(R.id.name)
        val mobile = findViewById<EditText>(R.id.phone)
        val email = findViewById<EditText>(R.id.email)
        val password = findViewById<EditText>(password)
        val male = findViewById<Button>(R.id.male)
        val female = findViewById<Button>(R.id.female)

        // Handle Get Started button click
        getStarted.setOnClickListener {
            val tname = name.text.toString().trim()
            val tmobile = mobile.text.toString().trim()
            val temail = email.text.toString().trim()
            val tpassword = password.text.toString().trim()

            // Firebase Authentication for sign up
            if (temail.isNotEmpty() && tpassword.isNotEmpty()) {
                auth.createUserWithEmailAndPassword(temail, tpassword)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            // Save the user's data to Firestore
                            val userId = auth.currentUser?.uid
                            if (userId != null) {
                                val userData = hashMapOf(
                                    "name" to tname,
                                    "mobile" to tmobile,
                                    "email" to temail,
                                    "role" to "",  // We will add the role (buyer/seller) after role selection
                                    "gender" to (if (male.isSelected) "Male" else "Female")

                                )

                                db.collection("users").document(userId)
                                    .set(userData)
                                    .addOnSuccessListener {
                                        // Redirect to role selection page after successful registration
                                        startActivity(Intent(this, RoleSelectiionActivity::class.java))
                                        finish()
                                    }
                                    .addOnFailureListener { e ->
                                        Toast.makeText(this, "Error saving user data: ${e.message}", Toast.LENGTH_SHORT).show()
                                    }
                            }
                        } else {
                            Toast.makeText(this, "Signup failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
            }
        }


        male.setOnClickListener {
            male.isSelected = true
            female.isSelected = false
        }

        female.setOnClickListener {
            female.isSelected = true
            male.isSelected = false
        }


        // Handle login button click
        login.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        // Back arrow functionality to go to previous screen
        backArrow.setOnClickListener {
            finish()  // Closes the current activity
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
//import android.widget.Toast
//import com.example.househuntingkt.R.id.password
//
//class RegisterActivity : AppCompatActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.register)
//
////        val backArrow = findViewById<ImageView>(R.id.backArrow)
//        val getStarted = findViewById<Button>(R.id.getStarted)
//        val login = findViewById<Button>(R.id.login)
//        val name = findViewById<EditText>(R.id.name)
//        val mobile = findViewById<EditText>(R.id.phone)
//        val email = findViewById<EditText>(R.id.email)
//        val password = findViewById<EditText>(password)
//        val male = findViewById<Button>(R.id.male)
//        val female = findViewById<Button>(R.id.female)
//        val buyer = findViewById<Button>(R.id.buyer)
//        val seller = findViewById<Button>(R.id.seller)
//        // Back to MainActivity (Home)
//
//        // Handle Get Started button click
//        getStarted.setOnClickListener {
//            val tname = name.text.toString().trim()
//            val tmobile = mobile.text.toString().trim()
//            val temail = email.text.toString().trim()
//            val tpassword = password.text.toString().trim()
//
//            if (tname.isEmpty() || tmobile.isEmpty() || temail.isEmpty() || tpassword.isEmpty()) {
//                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
//            } else {
//                // You can proceed with form submission logic here
//                Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show()
//            }
//        }
//
//        // Redirect to LoginActivity
//
//        login.setOnClickListener {
//            val intent = Intent(this, LoginActivity::class.java)
//            startActivity(intent)
//        }
//
//        // Back arrow functionality to go to RegisterActivity
//        getStarted.setOnClickListener {
//            val go = Intent(this, DashboardActivity::class.java)
//            startActivity(go)
//        }
//
//        buyer.setOnClickListener {
//            val buyerdashboard = Intent(this, BuyerDashboardActivity::class.java)
//            startActivity(buyerdashboard)
//        }
//
////        // Delay of 3 seconds (3000ms)
////        Handler(Looper.getMainLooper()).postDelayed({
////            // Redirect to ActivityMain
////            val intent = Intent(this@RegisterActivity, DashboardActivity::class.java)
////            startActivity(intent)
////            finish() // To prevent returning to this activity
////        }, 5000)
//
//
//    }
//}
