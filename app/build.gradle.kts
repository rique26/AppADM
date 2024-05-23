plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
    id("org.jetbrains.kotlin.android")
    id ("kotlin-kapt")

}

android {
    namespace = "com.example.appadm"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.appadm"
        minSdk = 23
        targetSdk = 33
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

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation ("com.google.firebase:firebase-firestore:25.0.0")
    implementation ("com.google.firebase:firebase-auth:23.0.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.firebase:firebase-common:21.0.0")
    implementation("androidx.activity:activity:1.8.0")
    implementation("com.google.ai.client.generativeai:common:0.4.0")
    implementation("com.google.ai.client.generativeai:generativeai:0.5.0")
    implementation("com.google.android.gms:play-services-fido:21.0.0")
    implementation("com.google.firebase:firebase-vertexai:16.0.0-beta01")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation(platform("com.google.firebase:firebase-bom:33.0.0"))

    //ViewModel e Livedata - Dependências para MVVM
    implementation ("androidx.lifecycle:lifecycle-extensions:2.2.0")


    //Retrofit - Biblioteca para requisições REST
    implementation ("com.google.code.gson:gson:2.8.6")
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

    implementation ("com.google.code.gson:gson:2.8.6")
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    kapt ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.0")



}