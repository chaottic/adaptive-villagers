plugins {
    kotlin("jvm") version "1.7.10"
    java
    id("org.quiltmc.loom") version "0.12-SNAPSHOT"
}

group = "com.chaottic"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")

    minecraft("com.mojang:minecraft:1.19")
    mappings(loom.layered {
        officialMojangMappings()
    })

    modImplementation("org.quiltmc:quilt-loader:0.17.1-beta.7-SNAPSHOT")
    modImplementation("org.quiltmc.quilt-kotlin-libraries:quilt-kotlin-libraries:0.1.0-SNAPSHOT")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}