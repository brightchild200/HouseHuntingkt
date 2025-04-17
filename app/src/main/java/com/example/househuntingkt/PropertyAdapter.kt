package com.example.househuntingkt

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class PropertyAdapter(private val propertyList: List<Property>) :
    RecyclerView.Adapter<PropertyAdapter.PropertyViewHolder>() {

    class PropertyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.property_image1)
        val nameTextView: TextView = itemView.findViewById(R.id.pg_name)
        val locationTextView: TextView = itemView.findViewById(R.id.pg_location)
        val descriptionTextView: TextView = itemView.findViewById(R.id.pg_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PropertyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.panvel, parent, false)
        return PropertyViewHolder(view)
    }

    override fun onBindViewHolder(holder: PropertyViewHolder, position: Int) {
        val property = propertyList[position]
        holder.nameTextView.text = property.name
        holder.locationTextView.text = property.location
        holder.descriptionTextView.text = property.description

        // Load from URL if available, fallback to placeholder
        if (property.imageUrl.isNotEmpty()) {
            Glide.with(holder.itemView.context)
                .load(property.imageUrl)
                .placeholder(R.drawable.pg1) // your default image
                .into(holder.imageView)
        } else {
            holder.imageView.setImageResource(R.drawable.pg1)
        }
    }

    override fun getItemCount(): Int = propertyList.size
}


//package com.example.househuntingkt
//
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Button
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView

//data class Property(val imageRes: Int, val name: String, val location: String, val description: String)
//
//class PropertyAdapter(private val propertyList: List<Property>) : RecyclerView.Adapter<PropertyAdapter.PropertyViewHolder>() {
//
//    class PropertyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        val imageView: ImageView = view.findViewById(R.id.property_image1)
//        val nameView: TextView = view.findViewById(R.id.property_name1)
//        val locationView: TextView = view.findViewById(R.id.property_location1)
//        val descriptionView: TextView = view.findViewById(R.id.property_de1)
//        val emailButton: Button = view.findViewById(R.id.email_btn1)
//        val whatsappButton: Button = view.findViewById(R.id.whatsapp_btn1)
//
//
//    }
//
//
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PropertyViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.panvel, parent, false)
//        return PropertyViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: PropertyViewHolder, position: Int) {
//        val property = propertyList[position]
//        holder.imageView.setImageResource(property.imageRes)
//        holder.nameView.text = property.name
//        holder.locationView.text = property.location
//        holder.descriptionView.text = property.description
//
//        // Set click listeners for buttons
//        holder.emailButton.setOnClickListener {
//            // Handle Email action
//        }
//
//        holder.whatsappButton.setOnClickListener {
//            // Handle WhatsApp action
//        }
//    }
//
//    override fun getItemCount() = propertyList.size
//}
