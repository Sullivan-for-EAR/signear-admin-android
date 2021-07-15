dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://plugins.gradle.org/m2/")
    }
}

rootProject.name = "signear-android"

include(
    ":app",
    ":data",
//    ":model",
    ":domain",
    ":ui-login",
    ":ui-reservation",
    ":common:core",
    ":common:ui-common"
)
