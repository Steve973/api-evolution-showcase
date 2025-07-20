rootProject.name = "api-evolution-showcase"

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}

// Include all main modules
include("00-the-blob")
include("01-faux-api")
include("02-the-superficial-split:api")
include("02-the-superficial-split:core")
include("03-service-factories:api")
include("03-service-factories:core")
include("04-api-spi-separation:api")
include("04-api-spi-separation:spi")
include("04-api-spi-separation:core")
include("04-api-spi-separation:services")
include("05-a-real-solution:api")
include("05-a-real-solution:core")
include("05-a-real-solution:services")
