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

class DashboardActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var propertyList: List<Property>
    private lateinit var backButton: ImageView
    private lateinit var vashi: Button
    private lateinit var seawoods: Button
    private lateinit var nerul: Button
    private lateinit var searchBar: EditText


    // Buyer or Seller bottom nav views (declared optionally)
    private var addPropertyTab: LinearLayout? = null
    private var requestsTab: LinearLayout? = null
    private var homeTab: LinearLayout? = null
    private var profileTab: LinearLayout? = null
    private var searchTab: LinearLayout? = null
    private var bookedTab: LinearLayout? = null
    private var listingTab: LinearLayout? = null
    private var wishlistTab: LinearLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard)

        // Role handling
        val role = intent.getStringExtra("role") ?: "buyer"
        Toast.makeText(this, "Logged in as $role", Toast.LENGTH_SHORT).show()

        // Load appropriate navbar layout
        val navbarContainer = findViewById<FrameLayout>(R.id.navbar_container)
        val inflater = LayoutInflater.from(this)
        val navbarView: View = when (role.lowercase()) {
            "seller" -> {
                inflater.inflate(R.layout.seller_navbar, navbarContainer, false)
            }
            "buyer" -> {
                inflater.inflate(R.layout.buyer_navbar, navbarContainer, false)
            }
            else -> { inflater.inflate(R.layout.seller_navbar, navbarContainer, false)}
        }
        navbarContainer.addView(navbarView)

        // Setup navbar buttons after it's added
        homeTab = navbarView.findViewById(R.id.nav_home)
        profileTab = navbarView.findViewById(R.id.nav_profile)

        if (role == "seller") {
            addPropertyTab = navbarView.findViewById(R.id.nav_property)
            requestsTab = navbarView.findViewById(R.id.nav_bookings)

            // Seller-specific listeners
            addPropertyTab?.setOnClickListener {
                Toast.makeText(this, "Add Property clicked", Toast.LENGTH_SHORT).show()
            }
            requestsTab?.setOnClickListener {
                Toast.makeText(this, "View Requests clicked", Toast.LENGTH_SHORT).show()
            }
        }
        if (role == "buyer") {
            searchTab = navbarView.findViewById(R.id.nav_search)
            bookedTab = navbarView.findViewById(R.id.nav_booked)
            listingTab = navbarView.findViewById(R.id.nav_listings)
            wishlistTab = navbarView.findViewById(R.id.nav_wishlist)

            // Buyer-specific listeners
            searchTab?.setOnClickListener {
                Toast.makeText(this, "Search clicked", Toast.LENGTH_SHORT).show()
            }
            wishlistTab?.setOnClickListener {
                Toast.makeText(this, "Wishlist clicked", Toast.LENGTH_SHORT).show()
            }
        }

        // Shared nav listeners
        homeTab?.setOnClickListener {
            Toast.makeText(this, "Home clicked", Toast.LENGTH_SHORT).show()
        }

        profileTab?.setOnClickListener {
            Toast.makeText(this, "Profile clicked", Toast.LENGTH_SHORT).show()
        }

        addPropertyTab?.setOnClickListener { startActivity(Intent(this, FormActivity::class.java)) }

        // Header menu button
//        backButton = findViewById(R.id.menu)
//        backButton.setOnClickListener {
//            startActivity(Intent(this, RegisterActivity::class.java))
//            finish()
//        }

        // City filters
        vashi = findViewById(R.id.btnCity2)
        seawoods = findViewById(R.id.btnCity3)
        nerul = findViewById(R.id.btnCity1)

        nerul.setOnClickListener { startActivity(Intent(this, Nerul::class.java)) }
        vashi.setOnClickListener { startActivity(Intent(this, Vashi::class.java)) }
        seawoods.setOnClickListener {
            Toast.makeText(this, "Coming soon!", Toast.LENGTH_SHORT).show()
        }

        // Search bar
        searchBar = findViewById(R.id.searchBar)
        searchBar.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                when (s.toString().trim().lowercase()) {
                    "vashi" -> startActivity(Intent(this@DashboardActivity, Vashi::class.java))
                    "nerul" -> startActivity(Intent(this@DashboardActivity, Nerul::class.java))
                    else -> Toast.makeText(this@DashboardActivity, "City not found", Toast.LENGTH_SHORT).show()
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        // Property data
        propertyList = listOf(
            Property(R.drawable.pg1, "Krishna Vandana PG", "Vichumbe, Panvel", "Our PG Basis..."),
            Property(R.drawable.pg2, "Silver Park Residency", "Karanjade", "Move into Rashi..."),
            Property(R.drawable.pg3, "Arvind PG Panvel", "Panvel", "Located in Panvel..."),
            Property(R.drawable.pg4, "Swami PG", "New Panvel", "Move into Swami PG..."),
            Property(R.drawable.pg5, "Kathani Housing Society", "Nerul", "Welcome to Kathani..."),
            Property(R.drawable.pg6, "Kathani Housing Society", "Nerul", "Welcome to Kathani...")
        )

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = PropertyAdapter(propertyList)
    }
}

//    private fun initBottomNavBar() {
//        propertyTab.setOnClickListener {
//            Toast.makeText(this, "Property tab clicked", Toast.LENGTH_SHORT).show()
//            // Replace with startActivity(Intent(this, SellerListingsActivity::class.java))
//        }
//
//        requestsTab.setOnClickListener {
//            Toast.makeText(this, "Requests tab clicked", Toast.LENGTH_SHORT).show()
//            // Replace with startActivity(Intent(this, RequestsActivity::class.java))
//        }
//
//        homeTab.setOnClickListener {
//            Toast.makeText(this, "Home tab clicked", Toast.LENGTH_SHORT).show()
//            // You’re already on Dashboard, maybe refresh
//        }
//
//        profileTab.setOnClickListener {
//            Toast.makeText(this, "Profile tab clicked", Toast.LENGTH_SHORT).show()
//            // Replace with startActivity(Intent(this, ProfileActivity::class.java))
//        }
//    }



//
//import android.content.Intent
//import android.os.Bundle
//import android.text.Editable
//import android.text.TextWatcher
//import android.widget.Button
//import android.widget.EditText
//import android.widget.ImageView
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//
//class DashboardActivity : AppCompatActivity() {
//
//    private lateinit var recyclerView: RecyclerView
//    private lateinit var propertyList: List<Property> // Define your property data model
//    private lateinit var backButton: ImageView // Assuming backButton is an ImageView
//    private lateinit var vashi: Button // Assuming panvelButton is a Button
//    private lateinit var seawoods: Button // Assuming panvelButton is a Button
//    private lateinit var nerul: Button // Assuming panvelButton is a Button
//    private lateinit var searchBar: EditText // Assuming searchBar is an EditText explicit
////    val propertyimage1 = findViewById<ImageView>(R.id.property_image1)  //implicit
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.dashboard) // Set dashboard as the main layout
//
//        // Initialize views
//        backButton = findViewById(R.id.menu) // Back button ID should match your layout
////        panvelButton = findViewById(R.id.btnCity1) // Panvel button ID should match your layout
//        searchBar = findViewById(R.id.searchBar) // Search bar ID should match your layout
//        vashi = findViewById(R.id.btnCity2)
//        seawoods = findViewById(R.id.btnCity3)
//        nerul = findViewById(R.id.btnCity1)
//
//        // Back arrow functionality to go to RegisterActivity
//        backButton.setOnClickListener {
//            val intent = Intent(this, RegisterActivity::class.java)
//            startActivity(intent)
//            finish()
//        }
//
//        nerul.setOnClickListener {
//            val intent = Intent(this, Nerul::class.java)
//            startActivity(intent)
//            finish()
//        }
//
//        vashi.setOnClickListener {
//            val intent = Intent(this, Vashi::class.java)
//            startActivity(intent)
//            finish()
//        }
//
////        seawoods.setOnClickListener {
////            val intent = Intent(this, ::class.java)
////            startActivity(intent)
////            finish()
////        }
////
//        // Initialize the property list with some data
//        propertyList = listOf(
//            Property(
//                R.drawable.pg1,
//                "Krishna Vandana PG",
//                "Vichumbe, Panvel, Navi Mumbai",
//                "Our PG Basis is available in the same flat In one flat family as well as separately staying girls on another side with a Separate toilet Bathroom, Cot, Bed Cupboard, Kitchen events available TV.\n"
//            ),
//            Property(
//                R.drawable.pg2,
//                "Silver Park Residency",
//                "Karanjade, New Panvel",
//                "Move into Rashi, a professionally managed PG home in Karanjade, navi mumbai. Located in a safe neighborhood, this female PG offers various modern amenities for your comfort, such as Power Backup, TV, etc. This PG has Double Occupancy types. This PG is nearby major commercial and educational hubs. Please contact the seller to book this fast selling high in demand PG stay. \n"
//            ),
//            Property(
//                R.drawable.pg3,
//                "Arvind PG Panvel",
//                "Panvel, Navi Mumbai",
//                "Located in Panvel, Navi Mumbai, Arvind PG Panvel is a Modern and Spacious PG Home that is Close to Major Educational Commercial hubs in the area. This Unisex PG offers all the Comforts like TV, AC, Food, Power Backup, Wi-Fi, etc. The PG has Strict Adherence to hygiene Standards and offers Single, Double, Triple, and Four Rooms. Please contact me in case you are interested or have Queried. Looking Forward to Serving you. \n"
//            ),
//            Property(
//                R.drawable.pg4,
//                "Swami PG",
//                "New Panvel, Navi Mumbai",
//                "Move into Swami PG, a professionally managed PG home in the New Panvel, Navi Mumbai. Located in a safe neighborhood, this male PG offers various modern amenities for your comfort. This PG has double occupancy types. This PG is nearby major commercial and educational hubs. Please contact the seller to book this fast selling high in demand PG stay. \n"
//            ),
//            Property(
//                R.drawable.pg5,
//                "Kathani Housing Society",
//                "Nerul, Navi Mumbai",
//                "Welcome to Kathani Housing Society, a well-maintained residential property located in the heart of Nerul, one of the most sought-after localities in Navi Mumbai. The society offers a blend of comfort, convenience, and community living, making it an ideal choice for families, working professionals, and students. \n"
//            ),
//            Property(
//                R.drawable.pg6,
//                "Kathani Housing Society",
//                "Nerul, Navi Mumbai",
//                "Welcome to Kathani Housing Society, a well-maintained residential property located in the heart of Nerul, one of the most sought-after localities in Navi Mumbai. The society offers a blend of comfort, convenience, and community living, making it an ideal choice for families, working professionals, and students. \n"
//            ),
//        )
//
//        // Set up the RecyclerView
//        recyclerView =
//            findViewById(R.id.recyclerView) // This assumes your RecyclerView ID is 'recyclerView' in dashboard.xml
//        recyclerView.layoutManager = LinearLayoutManager(this)
//        recyclerView.adapter =
//            PropertyAdapter(propertyList) // Connect the adapter to the RecyclerView
//
//        // Search functionality to redirect to PanvelActivity if "Panvel" is searched
//        searchBar.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//                // Not needed
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                // Not needed
//            }
//
//            override fun afterTextChanged(s: Editable?) {
//                when (s.toString().trim().lowercase()) {
////                    "panvel" -> {
////                        val intent = Intent(this@DashboardActivity, PanvelActivity::class.java)
////                        startActivity(intent)
////                    }
//                    "vashi" -> {
//                        val intent = Intent(this@DashboardActivity, Vashi::class.java)
//                        startActivity(intent)
//                    }
//                    "nerul" -> {
//                        val intent = Intent(this@DashboardActivity, Nerul::class.java)
//                        startActivity(intent)
//                    }
////                    "seawoods" -> {
////                        val intent = Intent(this@DashboardActivity, SeawoodsActivity::class.java)
////                        startActivity(intent)
////                    }
//                    else -> {
//                        // Optionally, you can show a message if no match is found
//                        Toast.makeText(this@DashboardActivity, "City not found", Toast.LENGTH_SHORT)
//                            .show()
//                    }
//                }
//            }
//        })
//    }
//}
//
