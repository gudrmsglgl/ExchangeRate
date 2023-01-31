object Dependencies {

    object KTX {
        val core by lazy { "androidx.core:core-ktx:1.7.0" }
    }

    object AndroidX {
        val appCompat by lazy { "androidx.appcompat:appcompat:1.5.1" }
        val activityKtx by lazy { "androidx.activity:activity-ktx:1.5.1" }
    }

    object Compose {
        private const val uiVersion = "1.1.1"
        val ui by lazy { "androidx.compose.ui:ui:$uiVersion" }
        val preview by lazy { "androidx.compose.ui:ui-tooling-preview:$uiVersion" }
        val material by lazy { "androidx.compose.material:material:$uiVersion"}

        val activity by lazy { "androidx.activity:activity-compose:1.3.1" }
        val uiTooling by lazy {"androidx.compose.ui:ui-tooling:$uiVersion" }

        val uiTestManifest by lazy { "androidx.compose.ui:ui-test-manifest:$uiVersion" }
        val uiTestJunit4 by lazy {"androidx.compose.ui:ui-test-junit4:$uiVersion" }

        // Optional - Integration with ViewModels
        val viewModel by lazy { "androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1" }
        // Optional - Integration with Hilt
        val hilt by lazy { "androidx.hilt:hilt-navigation-compose:1.0.0" }

        val numberPicker by lazy { "com.chargemap.compose:numberpicker:1.0.3" }
    }

    object Accompanist {
        val systemUiController by lazy { "com.google.accompanist:accompanist-systemuicontroller:0.29.0-alpha" }
        val pager by lazy { "com.google.accompanist:accompanist-pager:0.28.0" }
        val pagerIndicator by lazy { "com.google.accompanist:accompanist-pager-indicators:0.28.0" }
    }

    object LifeCycle {
        const val lifecycle_version = "2.3.1"
        val runtimeKtx by lazy { "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_version" }
        val compose by lazy { "androidx.lifecycle:lifecycle-runtime-compose:2.6.0-alpha01" }
        val viewModelKtx by lazy { "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version" }
    }


    object Dagger {
        val hilt by lazy { "com.google.dagger:hilt-android:2.44" }
        val hiltCompiler by lazy { "com.google.dagger:hilt-android-compiler:2.44" }
    }

    object Network {
        val retrofit2 by lazy { "com.squareup.retrofit2:retrofit:2.9.0" }
        val okhttp3 by lazy { "com.squareup.okhttp3:okhttp:4.9.1" }
        val okhttp3LoggingInterceptor by lazy { "com.squareup.okhttp3:logging-interceptor:4.8.0" }
        val serialization by lazy { "org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1" }
        val serializationAdapter by lazy { "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0" }
    }
}