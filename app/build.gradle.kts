import com.ttdrp.gameofthrones.buildsrc.AppConfig
import com.ttdrp.gameofthrones.buildsrc.Versions
import com.ttdrp.gameofthrones.buildsrc.Libs

plugins {
    id 'com.android.application'
    id 'kotlin-android'
    id 'kotlin-kapt'
}

android {
    compileSdk AppConfig.compileSdk
    buildToolsVersion AppConfig.buildToolsVersion

    defaultConfig {
        applicationId "com.ttdrp.gameofthrones"
        minSdk AppConfig.minSdk
        targetSdk AppConfig.targetSdk
        versionCode AppConfig.versionCode
        versionName AppConfig.versionName

        testInstrumentationRunner AppConfig.androidTestInstrumentation
    }

    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = '1.8'
        useIR = true
    }
    buildFeatures {
        compose true
    }
    composeOptions {
        kotlinCompilerExtensionVersion Versions.compose
        kotlinCompilerVersion Versions.kotlin
    }
}

dependencies {

    implementation Libs.AndroidX.coreKtx
    implementation Libs.AndroidX.AppCompat.appCompat
    implementation Libs.AndroidX.Activity.activityCompose
    implementation Libs.AndroidX.Compose.composeUi
    implementation Libs.AndroidX.Compose.composeUiTooling
    implementation Libs.AndroidX.Compose.composeMaterial
    implementation Libs.AndroidX.Compose.composeMaterialExtended
    implementation Libs.AndroidX.Lifecycle.runtime
    implementation Libs.AndroidX.Lifecycle.viewModel
    implementation Libs.Material.material
    implementCompose()

    implementation Libs.Squareup.Retrofit.retrofit
    implementation Libs.Squareup.Retrofit.moshiConverter
    implementation Libs.Squareup.Moshi.moshi

    testImplementation Libs.JUnit.junit
    androidTestImplementation Libs.AndroidX.Test.ext
    androidTestImplementation Libs.AndroidX.Test.espresso
    androidTestImplementation Libs.AndroidX.Test.uiTestJunit
}