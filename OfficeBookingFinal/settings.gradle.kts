pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        jcenter() // Warning: this repository is going to shut down soon
        maven { url=uri("https://jitpack.io")}
        maven {url=uri("https://developer.huawei.com/repo/")}
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        jcenter() // Warning: this repository is going to shut down soon
        maven { url=uri("https://jitpack.io")}
        maven {url=uri("https://developer.huawei.com/repo/")}
    }
}

rootProject.name = "OfficeBooking"
include(":app")
 