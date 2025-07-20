plugins {
    java
    id("io.freefair.lombok") version "8.14"
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

allprojects {
    apply(plugin = "java")
    apply(plugin = "io.freefair.lombok")

    group = "org.storck.medium"
    version = "1.0.0-SNAPSHOT"

    dependencies {
        implementation("ch.qos.logback:logback-classic:1.5.18")
    }
}
