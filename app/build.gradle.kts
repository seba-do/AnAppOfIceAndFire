plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    defaultConfig {
        applicationId = AppConfig.applicationId
        buildToolsVersion = AppConfig.buildToolsVersion

        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk
        compileSdk = AppConfig.compileSdk

        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

        testInstrumentationRunner = AppConfig.androidTestInstrumentation
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), AppConfig.proguardConsumerRules)
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
        kotlinCompilerExtensionVersion = Versions.compose
    }
}

dependencies {
    implementation(Libraries.AndroidX.coreKtx)
    implementation(Libraries.AndroidX.appCompat)
    implementation(Libraries.AndroidX.activityCompose)

    implementation(Libraries.Compose.ui)
    implementation(Libraries.Compose.uiTooling)
    implementation(Libraries.Compose.material)
    implementation(Libraries.Compose.materialExtended)
    implementation(Libraries.Compose.hiltNavigation)
    implementation(Libraries.Compose.paging)

    implementation(Libraries.Lifecycle.runtime)
    implementation(Libraries.Lifecycle.viewModel)
    implementation(Libraries.Lifecycle.viewModelCompose)

    implementation(Libraries.Room.room)
    implementation(Libraries.Room.roomKtx)
    implementation(Libraries.Room.roomPaging)
    kapt(Libraries.Room.roomKapt)

    implementation(Libraries.Hilt.hilt)
    kapt(Libraries.Hilt.hiltKapt)

    implementation(Libraries.Navigation.compose)

    implementation(Libraries.Squareup.retrofit)
    implementation(Libraries.Squareup.loggingInterceptor)
    implementation(Libraries.Squareup.moshiConverter)
    implementation(Libraries.Squareup.moshi)

    implementation(Libraries.Others.gson)

    testImplementation(Libraries.Test.junit)
    androidTestImplementation(Libraries.Test.ext)
    androidTestImplementation(Libraries.Test.espresso)
    androidTestImplementation(Libraries.Test.uiTestJunit)
}