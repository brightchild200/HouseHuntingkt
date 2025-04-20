package com.example.househuntingkt

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class PropertyAdapter(
    private val propertyList: List<Property>,
    private val context: Context,
    private val isSellerView: Boolean = false,  // Boolean flag to distinguish views
    private val deleteFunction: ((Property) -> Unit)? = null,  // Function for delete action
    private val wishlistFunction: ((Property) -> Unit)? = null  // Function for wishlist action
) : RecyclerView.Adapter<PropertyAdapter.PropertyViewHolder>() {

    // ViewHolder class for binding data
    class PropertyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.propertyTitle)
        val location: TextView = itemView.findViewById(R.id.propertyLocation)
        val price: TextView = itemView.findViewById(R.id.propertyPrice)
        val image: ImageView = itemView.findViewById(R.id.propertyImage)
        val emailButton: Button = itemView.findViewById(R.id.email_btn)
        val whatsappButton: Button = itemView.findViewById(R.id.whatsapp_btn)
        val actionButton: Button = itemView.findViewById(R.id.actionButton)  // Common action button
    }

    // Creating a view holder for each item
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PropertyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.property_item, parent, false)
        return PropertyViewHolder(view)
    }

    // Binding data to the view holder
    override fun onBindViewHolder(holder: PropertyViewHolder, position: Int) {
        val property = propertyList[position]
        holder.title.text = property.title
        holder.location.text = property.location
        holder.price.text = "₹${property.price}"
        Glide.with(holder.itemView.context).load(property.imageUrl).into(holder.image)

        // Action button logic for seller and buyer
        if (isSellerView) {
            holder.actionButton.text = "Delete"
            holder.actionButton.setBackgroundColor(context.getColor(R.color.red))  // Optional: Customize color
            holder.actionButton.setOnClickListener {
                deleteFunction?.invoke(property)  // Execute delete function if defined
            }
        } else {
            holder.actionButton.text = "Wishlist"
            holder.actionButton.setBackgroundColor(context.getColor(R.color.teal_700))  // Optional: Customize color
            holder.actionButton.setOnClickListener {
                wishlistFunction?.invoke(property)  // Execute wishlist function if defined
            }
        }

        // Email button logic
        holder.emailButton.setOnClickListener {
            val intent = android.content.Intent(android.content.Intent.ACTION_SENDTO).apply {
                data = android.net.Uri.parse("mailto:${property.email}")
            }
            holder.itemView.context.startActivity(intent)
        }

        // WhatsApp/Dialer button logic
        holder.whatsappButton.setOnClickListener {
            val intent = android.content.Intent(android.content.Intent.ACTION_DIAL).apply {
                data = android.net.Uri.parse("tel:${property.mobile}")
            }
            holder.itemView.context.startActivity(intent)
        }
    }

    // Return the total number of properties
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
