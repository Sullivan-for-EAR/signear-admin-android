import org.gradle.api.JavaVersion

object ProjectConfigurations {
    const val compileSdk = 30
    const val minSdk = 21
    const val targetSdk = 30
    const val buildTools = "30.0.3"

    const val appId = "com.sullivan.signearadmin"
    const val versionCode = 2
    const val versionName = "0.2.0"

    val javaVer = JavaVersion.VERSION_11
}