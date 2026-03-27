plugins {
    `java-library`
    id("com.gradleup.shadow").version("9.2.2")
    `maven-publish`
}

group = "com.github.angeschossen"
version = "7.25.4"
description = "LandsAPI"

// repo base options
var reposiliteRepoName = "IncrediblePlugins"
var reposiliteRepoUrl = "https://repo.incredibleplugins.com"

// publish repo
var reposilitePublishRepoId = "releases"
val reposilitePublishRepoName = reposiliteRepoName + reposilitePublishRepoId.split("-")
    .joinToString("") { it.replaceFirstChar { c -> c.uppercase() } }

// read repos
val reposiliteReadReleasesId = "releases"
val reposiliteReadReleasesName =
    reposiliteRepoName + reposiliteReadReleasesId.split("-").joinToString("") { it.replaceFirstChar { c -> c.uppercase() } }

repositories {
    mavenCentral()

    maven {
        url = uri("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")

        // As of Gradle 5.1, you can limit this to only those
        // dependencies you expect from it
        content {
            includeGroup("org.bukkit")
            includeGroup("org.spigotmc")
        }
    }

    /*
    As Spigot-API depends on the BungeeCord ChatComponent-API,
    we need to add the Sonatype OSS repository, as Gradle,
    in comparison to maven, doesn't want to understand the ~/.m2
    directory unless added using mavenLocal(). Maven usually just gets
    it from there, as most people have run the BuildTools at least once.
    This is therefore not needed if you're using the full Spigot/CraftBukkit,
    or if you're using the Bukkit API.
    */
    maven { url = uri("https://oss.sonatype.org/content/repositories/snapshots") }
    maven { url = uri("https://oss.sonatype.org/content/repositories/central") }
    maven { url = uri("https://jitpack.io") }

    maven {
        name = reposiliteReadReleasesName
        url = uri("$reposiliteRepoUrl/$reposiliteReadReleasesId")
        credentials(PasswordCredentials::class)
        authentication {
            create<BasicAuthentication>("basic")
        }

        mavenContent { releasesOnly() }
    }
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
    withSourcesJar()
    withJavadocJar()
}

tasks {
    build {
        dependsOn(shadowJar)
    }

    shadowJar {
        archiveClassifier.set("") // replace default
        configurations = listOf(project.configurations.shadow.get())
        relocate("com.github.angeschossen.pluginframework.api", "me.angeschossen.lands.api.framework")
        relocate("com.github.angeschossen.applicationframework.api", "me.angeschossen.lands.api.applicationframework")
    }

    withType<PublishToMavenRepository>().configureEach {
        doLast {
            val group = publication.groupId.replace('.', '/')
            val artifact = publication.artifactId
            val version = publication.version

            println("Published ${publication.groupId}:$artifact:$version")
            println("Browse artifact: $reposiliteRepoUrl/#/$reposilitePublishRepoId/$group/$artifact/$version")
            println("Browse Javadoc:  $reposiliteRepoUrl/javadoc/$reposilitePublishRepoId/$group/$artifact/$version")
        }
    }
}

dependencies {
    api("com.github.angeschossen:PluginFrameworkAPI:1.1.25")
    compileOnly("org.spigotmc:spigot-api:1.21.1-R0.1-SNAPSHOT")
    compileOnly("org.realityforge.org.jetbrains.annotations:org.jetbrains.annotations:1.7.0")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = project.group.toString()
            artifactId = project.description
            version = project.version.toString()

            artifact(tasks.named("shadowJar"))
            artifact(tasks.named("javadocJar"))
        }
    }

    repositories {
        maven {
            name = reposilitePublishRepoName
            url = uri("$reposiliteRepoUrl/$reposilitePublishRepoId")
            credentials(PasswordCredentials::class)
            authentication {
                create<BasicAuthentication>("basic")
            }
        }
    }
}