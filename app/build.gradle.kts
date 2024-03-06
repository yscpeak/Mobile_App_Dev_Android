plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.yschang.rickandmortyapi"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.yschang.rickandmortyapi"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        compose = true
    }
    composeOptions {
         kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }


}

dependencies {
    // 需要以下額外的依賴項來處理網路請求和資料解析
    // Retrofit for network requests
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    // Moshi for JSON parsing
    implementation("com.squareup.moshi:moshi:1.12.0")
    // Retrofit Moshi converter for allowing Retrofit to use Moshi for parsing data
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
    // OkHttp Logging Interceptor (This is optional, for logging network requests in debug mode)
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.0")
    // Coil for image loading
    implementation("io.coil-kt:coil-compose:2.1.0")

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7")

    implementation("androidx.compose.runtime:runtime-livedata")

    implementation("com.squareup.moshi:moshi-kotlin:1.12.0")



    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}