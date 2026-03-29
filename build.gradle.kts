plugins {
    id("com.incredibleplugins.base") version "1.0.26-SNAPSHOT"
}

group = "com.incredibleplugins"
version = "7.25.8"
description = "LandsAPI"

basePlugin {
    artifactName.set("lands-api")

    registerPublishRepo("https://repo.incredibleplugins.com/releases", "IncrediblePluginsReleases", true)
    registerDependencyRepo("https://repo.incredibleplugins.com/releases", "IncrediblePluginsReleases", false)
}

repositories {
    maven {
        url = uri("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
        content {
            includeGroup("org.bukkit")
            includeGroup("org.spigotmc")
        }
    }

    maven { url = uri("https://oss.sonatype.org/content/repositories/snapshots") }
    maven { url = uri("https://oss.sonatype.org/content/repositories/central") }
}

tasks {
    shadowJar {
        relocate("com.github.angeschossen.pluginframework.api", "me.angeschossen.lands.api.framework")
        relocate("com.github.angeschossen.applicationframework.api", "me.angeschossen.lands.api.applicationframework")
    }
}

dependencies {
    shadow("com.incredibleplugins:pluginframework-api:1.1.48")

    compileOnly("org.realityforge.org.jetbrains.annotations:org.jetbrains.annotations")
    compileOnly("org.spigotmc:spigot-api:1.21.1-R0.1-SNAPSHOT")
}