plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt)
    id(libs.plugins.kotlin.kapt.get().pluginId)
    alias(libs.plugins.paparazzi)
}

android {
    namespace = "com.agile.ktorptoject"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.agile.ktorptoject"
        minSdk = 28
        targetSdk = 35
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
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
        compose = true
    }
    hilt {
        enableAggregatingTask = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.8"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.bundles.androidxCore)
    implementation(libs.bundles.lifecycle)
    implementation(libs.bundles.compose)
    implementation(libs.bundles.composeAdditional)
    implementation(libs.bundles.navigation)
    implementation(libs.bundles.hilt)
//    implementation(libs.bundles.datastore)
    implementation(libs.bundles.otherLibraries)
    implementation(libs.bundles.retrofit)
    implementation(libs.bundles.datastore)
    implementation(libs.androidx.media3.session)
    kapt(libs.bundles.hiltKapt)
    annotationProcessor(libs.hiltCompiler)
    debugImplementation(libs.composeUiTooling)
    debugImplementation(libs.androidx.compose.ui.testManifest)
    testImplementation(libs.bundles.testing)
    androidTestImplementation(libs.bundles.uiTesting)

    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.android) // Android için Ktor istemcisi
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.ktor.client.logging) // versiyonu diğer Ktor versiyonlarına göre uyumlu yap

    implementation ("androidx.compose.material3:material3:1.2.0")
    implementation( "androidx.compose.material3:material3-window-size-class:1.2.0" )// Opsiyonel

    implementation(project(":domain"))
    implementation(project(":data"))
    implementation(project(":common"))


}
