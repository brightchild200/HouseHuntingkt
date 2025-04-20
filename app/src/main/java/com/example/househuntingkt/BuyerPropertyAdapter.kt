//package com.example.househuntingkt
//
//// File: BuyerPropertyAdapter.kt
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//
//class BuyerPropertyAdapter(private val list: List<BuyerProperty>) :
//    RecyclerView.Adapter<BuyerPropertyAdapter.ViewHolder>() {
//
//    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val propertyImage: ImageView = itemView.findViewById(R.id.property_image)
//        val propertyTitle: TextView = itemView.findViewById(R.id.property_title)
//        val propertyLocation: TextView = itemView.findViewById(R.id.property_location)
//        val propertyPrice: TextView = itemView.findViewById(R.id.property_price)
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val view = LayoutInflater.from(parent.context)
//            .inflate(R.layout.panvel, parent, false)
//        return ViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val property = list[position]
//        holder.propertyImage.setImageResource(property.imageResId)
//        holder.propertyTitle.text = property.title
//        holder.propertyLocation.text = property.location
//        holder.propertyPrice.text = property.price
//    }
//
//    override fun getItemCount(): Int = list.size
//}


package com.example.househuntingkt


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BuyerPropertyAdapter(private val propertyListBuyer: List<PropertyBuyer>) : RecyclerView.Adapter<BuyerPropertyAdapter.BuyerPropertyViewHolder>() {

    class BuyerPropertyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.property_image1)
        val nameView: TextView = view.findViewById(R.id.property_name1)
        val locationView: TextView = view.findViewById(R.id.property_location1)
        val descriptionView: TextView = view.findViewById(R.id.property_de1)
        val emailButton: Button = view.findViewById(R.id.email_btn1)
        val whatsappButton: Button = view.findViewById(R.id.whatsapp_btn1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuyerPropertyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.panvel, parent, false) // Use your actual layout file here
        return BuyerPropertyViewHolder(view)
    }

    override fun onBindViewHolder(holder: BuyerPropertyViewHolder, position: Int) {
        val property = propertyListBuyer[position]
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

    override fun getItemCount() = propertyListBuyer.size
}


//data class PropertyBuyer(val imageRes: Int, val name: String, val location: String, val description: String)
//
//class BuyerPropertyAdapter(private val propertyListBuyer: List<PropertyBuyer>) : RecyclerView.Adapter<BuyerPropertyAdapter.BuyerPropertyViewHolder>() {
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
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuyerPropertyViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.panvel, parent, false)
//        return BuyerPropertyViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: BuyerPropertyViewHolder, position: Int) {
//        val property = propertyListBuyer[position]
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
//    override fun getItemCount() = propertyListBuyer.size
//}
