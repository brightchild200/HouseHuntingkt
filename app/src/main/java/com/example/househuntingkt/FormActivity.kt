package com.example.househuntingkt

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.asRequestBody
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import okhttp3.*
import org.json.JSONObject
import java.io.File
import java.io.IOException

class FormActivity : AppCompatActivity() {

    private val IMAGE_REQUEST_CODE = 100
    private val firestore = FirebaseFirestore.getInstance()

    private lateinit var pgName: EditText
    private lateinit var pgLocation: EditText
    private lateinit var pgPrice: EditText
    private lateinit var pgNearby: EditText
    private lateinit var pgDescription: EditText
    private lateinit var pgOccupancy: EditText
    private lateinit var pgAmenities: EditText
    private lateinit var pgFacilities: EditText
    private lateinit var pgAccessibility: EditText
    private lateinit var pgContact: EditText
    private lateinit var uploadImageButton: Button
    private lateinit var imagePreviewList: RecyclerView
    private lateinit var submitButton: Button
    private lateinit var imagePreviewAdapter: ImagePreviewAdapter

    private var imageUris: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pgform)

        pgName = findViewById(R.id.pg_name)
        pgLocation = findViewById(R.id.pg_location)
        pgPrice = findViewById(R.id.pg_price)
        pgNearby = findViewById(R.id.pg_nearby)
        pgDescription = findViewById(R.id.pg_description)
        pgOccupancy = findViewById(R.id.pg_occupancy)
        pgAmenities = findViewById(R.id.pg_amenities)
        pgFacilities = findViewById(R.id.pg_facilities)
        pgAccessibility = findViewById(R.id.pg_accessibility)
        pgContact = findViewById(R.id.pg_contact)
        uploadImageButton = findViewById(R.id.upload_image_button)
        imagePreviewList = findViewById(R.id.image_preview_list)
        submitButton = findViewById(R.id.rent_pg)

        imagePreviewAdapter = ImagePreviewAdapter(imageUris)
        imagePreviewList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        imagePreviewList.adapter = imagePreviewAdapter

        uploadImageButton.setOnClickListener {
            // Open image picker to select images
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            startActivityForResult(intent, IMAGE_REQUEST_CODE)
        }

        submitButton.setOnClickListener {
            submitForm()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            if (data?.clipData != null) {
                val count = data.clipData!!.itemCount
                for (i in 0 until count) {
                    val imageUri = data.clipData!!.getItemAt(i).uri
                    imageUris.add(imageUri.toString())
                }
            } else {
                val imageUri = data?.data
                imageUris.add(imageUri.toString())
            }
            imagePreviewAdapter.notifyDataSetChanged()
            imagePreviewList.visibility = View.VISIBLE
        }
    }

    private fun submitForm() {
        val name = pgName.text.toString().trim()
        val location = pgLocation.text.toString().trim()
        val price = pgPrice.text.toString().trim()
        val nearby = pgNearby.text.toString().trim()
        val description = pgDescription.text.toString().trim()
        val occupancy = pgOccupancy.text.toString().trim()
        val amenities = pgAmenities.text.toString().trim()
        val facilities = pgFacilities.text.toString().trim()
        val accessibility = pgAccessibility.text.toString().trim()
        val contact = pgContact.text.toString().trim()

        if (name.isEmpty() || location.isEmpty() || price.isEmpty() || imageUris.isEmpty()) {
            // Show error message
            return
        }

        // Upload images to ImgBB and get URLs
        val imageUrls = ArrayList<String>()
        for (uri in imageUris) {
            uploadImageToImgBB(uri) { imageUrl ->
                imageUrls.add(imageUrl)
                if (imageUrls.size == imageUris.size) {
                    // All images uploaded, save data to Firestore
                    saveToFirestore(name, location, price, nearby, description, occupancy, amenities, facilities, accessibility, contact, imageUrls)
                }
            }
        }
    }

    private fun uploadImageToImgBB(imageUri: String, callback: (String) -> Unit) {
        val client = OkHttpClient()
        val file = File(imageUri)  // You might need to adjust for URI to File conversion

        val mediaType = "image/*".toMediaTypeOrNull()
        val requestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("key", "7a1781c9f86b448f2ea0e71c9c25aad9")  // Replace with your ImgBB API Key
            .addFormDataPart("image", file.name, file.asRequestBody(mediaType))
            .build()

        val request = Request.Builder()
            .url("https://api.imgbb.com/1/upload")
            .post(requestBody)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                // Handle failure
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val jsonResponse = JSONObject(response.body?.string() ?: "")
                    val imageUrl = jsonResponse.getJSONObject("data").getString("url")
                    callback(imageUrl)  // Pass the image URL to callback
                }
            }
        })
    }

    private fun saveToFirestore(name: String, location: String, price: String, nearby: String, description: String, occupancy: String, amenities: String, facilities: String, accessibility: String, contact: String, imageUrls: ArrayList<String>) {
        val pgData = hashMapOf(
            "name" to name,
            "location" to location,
            "price" to price,
            "nearby" to nearby,
            "description" to description,
            "occupancy" to occupancy,
            "amenities" to amenities,
            "facilities" to facilities,
            "accessibility" to accessibility,
            "contact" to contact,
            "images" to imageUrls
        )

        firestore.collection("PGs").add(pgData)
            .addOnSuccessListener {
                // Successfully saved data
                finish() // Close the activity
            }
            .addOnFailureListener {
                // Handle failure
            }
    }
}
