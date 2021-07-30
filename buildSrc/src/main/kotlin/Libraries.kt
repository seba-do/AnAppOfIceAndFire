object Libraries {

    object Classpath {
        const val gradle = "com.android.tools.build:gradle:${Versions.classpathGradle}"
        const val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    }

    object AndroidX {
        const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
        const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
        const val activityCompose = "androidx.activity:activity-compose:${Versions.activityCompose}"
    }

    object Compose {
        const val ui = "androidx.compose.ui:ui:${Versions.compose}"
        const val material = "androidx.compose.material:material:${Versions.compose}"
        const val materialExtended = "androidx.compose.material:material-icons-extended:${Versions.compose}"
        const val uiTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
    }

    object Lifecycle {
        const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
        const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    }

    object Squareup {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val moshiConverter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
        const val moshi = "com.squareup.moshi:moshi-kotlin:${Versions.moshi}"
        const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"
    }

    object Test {
        const val ext = "androidx.test.ext:junit:${Versions.ext}"
        const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
        const val uiTestJunit = "androidx.compose.ui:ui-test-junit4:${Versions.compose}"
        const val junit = "junit:junit:${Versions.junit}"
    }
}