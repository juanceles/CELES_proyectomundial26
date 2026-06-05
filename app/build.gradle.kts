plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    // OBLIGATORIO: Plugin para que funcione @Serializable en tus clases de datos
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "Nos.Fuimos.Mudiales"
    compileSdk = 35

    defaultConfig {
        applicationId = "Nos.Fuimos.Mudiales"
        minSdk = 31
        targetSdk = 35
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
        // Actualizado a Java 17 para evitar fallos con Compose moderno y SDK 35
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        // Actualizado a Java 17
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    // AndroidX & Jetpack Compose Core
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Jetpack DataStore (Preferencias Locales Asíncronas)
    implementation(libs.androidx.datastore.preferences)

    // Kotlinx Serialization (Librería Core de JSON)
    implementation(libs.kotlinx.serialization.json)

    // Retrofit HTTP Client
    implementation(libs.retrofit)

    // Convertidor oficial de Retrofit para Kotlinx Serialization
    implementation(libs.retrofit.converter.kotlinx.serialization)
}