package com.example.househuntingkt

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DashboardActivity: AppCompatActivity() {

    private lateinit var backButton: ImageView
    private lateinit var panvelButton: Button
    private lateinit var searchBar: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard)

        // Initialize views
        backButton = findViewById(R.id.menu)
        panvelButton = findViewById(R.id.btnCity1)
        searchBar = findViewById(R.id.searchBar)

        // Back arrow functionality to go to RegisterActivity
        backButton.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Panvel button click listener to go to PanvelActivity
        panvelButton.setOnClickListener {
            val intent = Intent(this, PanvelActivity::class.java)
            startActivity(intent)
        }

        // Search functionality to redirect to PanvelActivity if "Panvel" is searched
        searchBar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Not needed
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Not needed
            }

            override fun afterTextChanged(s: Editable?) {
                if (s.toString().equals("Panvel", true)) {
                    val intent = Intent(this@DashboardActivity, PanvelActivity::class.java)
                    startActivity(intent)
                }
            }
        })

        // RecyclerView setup
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)


            // Add more properties here

    }
}
