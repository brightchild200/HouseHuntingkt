package com.example.househuntingkt

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Panvel(private val propertyList: List<Property>) :
    RecyclerView.Adapter<Panvel.PropertyViewHolder>() {

    class PropertyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val propertyImage1: ImageView = itemView.findViewById(R.id.property_image1)
        val propertyName1: TextView = itemView.findViewById(R.id.property_name1)

        val propertyLocation1: TextView = itemView.findViewById(R.id.property_location1)
        val emailButton1: Button = itemView.findViewById(R.id.email_button1)
        val whatsappButton1: Button = itemView.findViewById(R.id.whatsapp_button1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PropertyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.panvel, parent, false)
        return PropertyViewHolder(view)
    }

    override fun onBindViewHolder(holder: PropertyViewHolder, position: Int) {
        val property = propertyList[position]
        holder.propertyName1.text = property.name
        holder.propertyLocation1.text = property.location
        // Set image and buttons accordingly
    }

    override fun getItemCount(): Int {
        return propertyList.size
    }

    data class Property(
        val name: String,
        val developer: String,
        val location: String,
        val imageRes: Int // Drawable resource for the image
    )

}
