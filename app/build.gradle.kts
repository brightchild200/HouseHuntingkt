plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
   // id("com.android.application")
    id("com.google.gms.google-services")
    //id ("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}

android {
    namespace = "com.example.househuntingkt"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.househuntingkt"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }


    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.firebase.storage)
    implementation(libs.firebase.firestore.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    implementation("androidx.cardview:cardview:1.0.0")
// In app-level build.gradle
    implementation ("com.google.firebase:firebase-auth:22.3.0")
    implementation ("com.google.firebase:firebase-firestore:24.9.0")

    androidTestImplementation(libs.androidx.espresso.core)
//    implementation("com.google.android.gms:play-services-maps:19.1.0")
//    implementation ("com.google.android.gms:play-services-location:21.0.1")
//    // Import the Firebase BoM
    implementation(platform("com.google.firebase:firebase-bom:33.12.0"))

    // TODO: Add the dependencies for Firebase products you want to use
    // When using the BoM, don't specify versions in Firebase dependencies
    implementation("com.google.firebase:firebase-analytics")

    // Declare the dependency for the Cloud Firestore library
    // When using the BoM, you don't specify versions in Firebase library dependencies
    implementation("com.google.firebase:firebase-firestore")

    // Add the dependencies for any other desired Firebase products
    // https://firebase.google.com/docs/android/setup#available-libraries
    implementation ("com.google.firebase:firebase-firestore-ktx:24.9.0")
    implementation ("com.google.firebase:firebase-storage-ktx")
    implementation ("androidx.core:core-ktx:1.12.0")
    implementation ("androidx.appcompat:appcompat:1.6.1")
//    image handling
    implementation ("com.squareup.okhttp3:okhttp:4.10.0")
    implementation("com.squareup.okhttp3:okhttp-urlconnection:4.10.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")
    implementation("com.squareup.okhttp3:okhttp:4.10.0")
    implementation ("com.github.bumptech.glide:glide:4.16.0")
    implementation ("androidx.recyclerview:recyclerview:1.3.2")
    implementation ("com.squareup.picasso:picasso:2.71828")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.16.0")
    implementation ("androidx.appcompat:appcompat:1.6.1")
    implementation ("androidx.constraintlayout:constraintlayout:2.1.4")


}








