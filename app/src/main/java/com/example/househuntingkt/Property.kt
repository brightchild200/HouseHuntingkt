package com.example.househuntingkt

data class Property(
    val imageUrl: String,
    val title: String,
    val location: String,
    val description: String,
    val price: String, // ← Yeh line add karo
    val email: String,
    val mobile: String,
    val sellerId: String = ""   // 🟢 Yeh new field add karo
)


//package com.example.househuntingkt
//
//data class Property(
//    val imageUrl: String = "",
//    val title: String = "",
//    val location: String = "",
//    val description: String = ""
//)
