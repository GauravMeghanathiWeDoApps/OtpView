import org.gradle.kotlin.dsl.from
import kotlin.text.set

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("maven-publish")
}

android {
    namespace = "com.mg.otpview"
    compileSdk = 35

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = "com.mg"
            artifactId = "otpview"
            version = "1.0.0"
            afterEvaluate{
                from(components["release"])
            }
        }
    }
}

repositories {
    maven {
        name = "GithubPackages"
        url = uri("https://maven.pkg.github.com/GauravMeghanathiWeDoApps/OtpView")
        credentials {
            username = "GauravMeghanathiWeDoApps"
            password = "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"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

