plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.essayrent"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.essayrent"
        minSdk = 25
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        multiDexEnabled = true
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
    sourceSets {
        getByName("main") {
            assets {
                srcDirs("src\\main\\assets", "src\\main\\assets",
                    "src\\main\\assets",
                    "src\\main\\assets\\2"
                )
            }
        }
    }
    buildFeatures {
        viewBinding = true
    }

}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation( "com.airbnb.android:lottie:6.2.0")

    implementation ("com.squareup.retrofit2:retrofit:2.9.0")

    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.google.firebase:firebase-auth-ktx:22.2.0")
    implementation("com.google.firebase:firebase-auth:22.3.0")
    implementation ("com.squareup.picasso:picasso:2.3.2")
    implementation("com.android.volley:volley:1.2.1")
    implementation ("com.nineoldandroids:library:2.4.0")
    implementation ("com.daimajia.slider:library:1.1.5@aar")
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")
    implementation("com.google.firebase:firebase-inappmessaging:20.4.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    testImplementation("junit:junit:4.13.2")
    implementation ("com.razorpay:checkout:1.6.4")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation ("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2")
    implementation ("com.android.support:multidex:1.0.3")

}