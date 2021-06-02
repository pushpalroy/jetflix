package com.fabler.jetflix.buildsrc

object Versions {
  const val ktlint = "0.41.0"
}

object Libs {
  private const val agpVersion = "7.0.0-beta03"
  const val androidGradlePlugin = "com.android.tools.build:gradle:$agpVersion"
  const val junit = "junit:junit:4.13"

  object Accompanist {
    const val version = "0.10.0"
    const val coil = "com.google.accompanist:accompanist-coil:$version"
    const val insets = "com.google.accompanist:accompanist-insets:$version"
    const val systemuicontroller = "com.google.accompanist:accompanist-systemuicontroller:$version"
  }

  object Hilt {
    private const val hiltVersion = "2.36"
    const val hiltAndroidGradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:$hiltVersion"
    const val android = "com.google.dagger:hilt-android:$hiltVersion"
    const val lifecycleViewModel = "androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03"
    const val androidCompiler = "com.google.dagger:hilt-android-compiler:$hiltVersion"
    const val compiler = "androidx.hilt:hilt-compiler:1.0.0"
  }

  object Retrofit {
    private const val retrofitVersion = "2.9.0"
    const val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
    const val converterGson = "com.squareup.retrofit2:converter-gson:$retrofitVersion"
  }

  object Exoplayer {
    private const val exoVersion = "2.14.0"
    const val exoPlayer = "com.google.android.exoplayer:exoplayer:$exoVersion"
    const val mediaSession = "com.google.android.exoplayer:extension-mediasessio:$exoVersion"
  }

  object Kotlin {
    const val version = "1.4.32"
    const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$version"
    const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
    const val extensions = "org.jetbrains.kotlin:kotlin-android-extensions:$version"
  }

  object Coroutines {
    private const val version = "1.4.1"
    const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
    const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
    const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
  }

  object LottieView {
    private const val lottieVersion = "3.7.0"
    private const val lottieComposeVersion = "1.0.0-beta07-1"
    const val lottie = "com.airbnb.android:lottie:${lottieVersion}"
    const val lottieCompose = "com.airbnb.android:lottie-compose:${lottieComposeVersion}"
  }

  object AndroidX {
    private const val appCompatVer = "1.3.0"
    private const val coreKtxVer = "1.6.0-alpha03"
    private const val navComposeVer = "2.4.0-alpha01"
    const val appcompat = "androidx.appcompat:$appCompatVer"
    const val coreKtx = "androidx.core:core-ktx:$coreKtxVer"
    const val navigation = "androidx.navigation:navigation-compose:$navComposeVer"

    object Activity {
      private const val activityComposeVer = "1.3.0-alpha08"
      const val activityCompose = "androidx.activity:activity-compose:$activityComposeVer"
    }

    object Compose {
      const val snapshot = ""
      const val composeVer = "1.0.0-beta07"

      const val runtime = "androidx.compose.runtime:runtime:$composeVer"
      const val foundation = "androidx.compose.foundation:foundation:${composeVer}"
      const val layout = "androidx.compose.foundation:foundation-layout:${composeVer}"
      const val ui = "androidx.compose.ui:ui:${composeVer}"
      const val uiUtil = "androidx.compose.ui:ui-util:${composeVer}"
      const val material = "androidx.compose.material:material:${composeVer}"
      const val animation = "androidx.compose.animation:animation:${composeVer}"
      const val tooling = "androidx.compose.ui:ui-tooling:${composeVer}"
      const val iconsExtended = "androidx.compose.material:material-icons-extended:$composeVer"
      const val constraintLayout = "androidx.constraintlayout:constraintlayout-compose:1.0.0-alpha07"

      // Test rules and transitive dependencies
      const val uiTestJunit = "androidx.compose.ui:ui-test-junit4:$composeVer"

      // Needed for createComposeRule, but not createAndroidComposeRule
      const val uiTestManifest = "androidx.compose.ui:ui-test-manifest:$composeVer"
    }

    object Lifecycle {
      private const val vmComposeVer = "1.0.0-alpha05"
      const val viewModelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:$vmComposeVer"
    }

    object Test {
      private const val version = "1.3.0"
      const val core = "androidx.test:core:$version"
      const val rules = "androidx.test:rules:$version"

      object Ext {
        private const val version = "1.1.2-rc01"
        const val junit = "androidx.test.ext:junit-ktx:$version"
      }

      const val espressoCore = "androidx.test.espresso:espresso-core:3.2.0"
    }
  }

  object ConstraintLayout {
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.0.4"
  }

  object Logging {
    private val timber = "com.jakewharton.timber:timber:4.7.1"
  }
}
