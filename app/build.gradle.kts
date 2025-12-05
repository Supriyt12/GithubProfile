plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    kotlin("kapt")          // untuk compiler Glide
}

android {
    namespace = "id.ac.polbeng.supriyanto.githubprofile"
    compileSdk = 36

    defaultConfig {
        applicationId = "id.ac.polbeng.supriyanto.githubprofile"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        // Token Github – ganti dengan token punyamu sendiri
        buildConfigField(
            "String",
            "ACCESS_TOKEN",
            "\"ghp_UDLi62PZ6tpkBTIHQnAtHRlBt7M4uN3nvPPT\""
        )

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

    // ==== PERBAIKAN JVM-TARGET (BIAR GAK BENTROK) ====
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Lottie (animasi splashscreen)
    implementation("com.airbnb.android:lottie:5.2.0")

    // CircleImageView (foto bulat)
    implementation("de.hdodenhof:circleimageview:3.1.0")

    // Glide (load gambar dari URL)
    implementation("com.github.bumptech.glide:glide:4.13.2")
    kapt("com.github.bumptech.glide:compiler:4.13.2")

    // Retrofit (HTTP client)
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // OkHttp logging interceptor
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.9")
}
