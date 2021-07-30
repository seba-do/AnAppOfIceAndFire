package com.ttdrp.gameofthrones.buildsrc

import org.gradle.api.artifacts.dsl.DependencyHandler

object Versions {
    const val ktlint = "0.40.0"
    const val kotlin = "1.4.31"
    const val compose = "1.0.0"
}

object Libs {
    const val androidGradlePlugin = "com.android.tools.build:gradle:7.0.0"

    object Kotlin {
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
        const val extensions = "org.jetbrains.kotlin:kotlin-android-extensions:${Versions.kotlin}"
    }

    object AndroidX {
        const val coreKtx = "androidx.core:core-ktx:1.5.0-beta01"

        object Activity {
            const val activityCompose = "androidx.activity:activity-compose:1.3.0-alpha03"
        }

        object AppCompat {
            private const val version = "1.2.0"
            const val appCompat = "androidx.appcompat:appcompat:$version"
        }

        object Compose {
            private const val version = "1.0.0"

            const val composeUi = "androidx.compose.ui:ui:${Versions.compose}"
            const val composeMaterial = "androidx.compose.material:material:${Versions.compose}"
            const val composeMaterialExtended = "androidx.compose.material:material-icons-extended:${Versions.compose}"
            const val composeUiTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"

            fun DependencyHandler.implementCompose() {
                add("implementation", composeUi)
                add("implementation", composeMaterial)
                add("implementation", composeMaterialExtended)
                add("implementation", composeUiTooling)
            }
        }

        object Fragment {
            private const val version = "1.3.0"
            const val fragment = "androidx.fragment:fragment-ktx:$version"
        }

        object Lifecycle {
            const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:2.3.0"
            const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.0"
            const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:2.2.0"

            const val viewModelCompose =
                "androidx.lifecycle:lifecycle-viewmodel-compose:1.0.0-alpha02"
        }

        object ConstraintLayout {
            const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.0.4"

            const val constraintLayoutCompose =
                "androidx.constraintlayout:constraintlayout-compose:1.0.0-alpha03"
        }

        object Navigation {
            private const val version = "2.3.3"
            const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:$version"
            const val navigationUi = "androidx.navigation:navigation-ui-ktx:$version"
            const val navigationDynamicFeatures = "androidx.navigation:navigation-dynamic-features-fragment:$version"
            const val gradlePlugin = "android.arch.navigation:navigation-safe-args-gradle-plugin:1.0.0"
        }

        object Room {
            private const val version = "2.2.6"
            const val roomRuntime = "androidx.room:room-runtime:$version"
            const val roomCompiler = "androidx.room:room-compiler:$version"
        }

        object Test {
            const val ext = "androidx.test.ext:junit:1.1.2"
            const val espresso = "androidx.test.espresso:espresso-core:3.3.0"

            const val uiTestJunit = "androidx.compose.ui:ui-test-junit4:${Versions.compose}"
        }
    }

    object Material {
        private const val version = "1.3.0"
        const val material = "com.google.android.material:material:$version"
    }

    object Squareup {
        object Retrofit {
            private const val version = "2.9.0"
            const val retrofit = "com.squareup.retrofit2:retrofit:$version"
            const val moshiConverter = "com.squareup.retrofit2:converter-moshi:$version"
        }

        object Moshi {
            private const val version = "1.9.3"
            const val moshi = "com.squareup.moshi:moshi-kotlin:$version"
        }

        object OKHTTP {
            private const val version = "4.4.0"
            const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:$version"
        }
    }

    object JUnit {
        private const val version = "4.13.2"
        const val junit = "junit:junit:$version"
    }
}