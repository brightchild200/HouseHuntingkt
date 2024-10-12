package com.example.househuntingkt

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class Property(val imageRes: Int, val name: String, val location: String, val description: String)

class PropertyAdapter(private val propertyList: List<Property>) : RecyclerView.Adapter<PropertyAdapter.PropertyViewHolder>() {

    class PropertyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.property_image1)
        val nameView: TextView = view.findViewById(R.id.property_name1)
        val locationView: TextView = view.findViewById(R.id.property_location1)
        val descriptionView: TextView = view.findViewById(R.id.property_de1)
        val emailButton: Button = view.findViewById(R.id.email_btn1)
        val whatsappButton: Button = view.findViewById(R.id.whatsapp_btn1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PropertyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.panvel, parent, false)
        return PropertyViewHolder(view)
    }

    override fun onBindViewHolder(holder: PropertyViewHolder, position: Int) {
        val property = propertyList[position]
        holder.imageView.setImageResource(property.imageRes)
        holder.nameView.text = property.name
        holder.locationView.text = property.location
        holder.descriptionView.text = property.description

        // Set click listeners for buttons
        holder.emailButton.setOnClickListener {
            // Handle Email action
        }

        holder.whatsappButton.setOnClickListener {
            // Handle WhatsApp action
        }
    }

    override fun getItemCount() = propertyList.size
}
