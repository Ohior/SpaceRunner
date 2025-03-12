plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.android.kotlin.multiplatform.library)
    alias(libs.plugins.compose)
    id("org.jetbrains.kotlin.plugin.compose") version "2.1.10"
}

kotlin {

    androidLibrary {
        namespace = "ohior.app.pearplatform"
        compileSdk = 35
        minSdk = 21

        withHostTestBuilder {
        }

        withDeviceTestBuilder {
            sourceSetTreeName = "test"
        }.configure {
            instrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }
    }

    val xcfName = "pearplatformKit"

    iosX64 {
        binaries.framework {
            baseName = xcfName
        }
    }

    iosArm64 {
        binaries.framework {
            baseName = xcfName
        }
    }

    iosSimulatorArm64 {
        binaries.framework {
            baseName = xcfName
        }
    }


    jvm()

    wasmJs {
        browser()
        binaries.executable()
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.kotlin.stdlib)
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)            }
        }

        commonTest {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }

        androidMain {
            dependencies {
                implementation(libs.androidx.activityCompose)
            }
        }

//        getByName("androidDeviceTest") {
//            dependencies {
//                implementation(libs.androidx.runner)
//                implementation(libs.androidx.core)
//                implementation(libs.androidx.junit)
//            }
//        }

        iosMain {
            dependencies {
                // Add iOS-specific dependencies here. This a source set created by Kotlin Gradle
                // Plugin (KGP) that each specific iOS target (e.g., iosX64) depends on as
                // part of KMP’s default source set hierarchy. Note that this source set depends
                // on common by default and will correctly pull the iOS artifacts of any
                // KMP dependencies declared in commonMain.
            }
        }

        val jvmMain by getting {
            dependencies {
            }
        }
    }
}