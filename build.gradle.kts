plugins {
    kotlin("jvm") version "1.9.22"
    kotlin("plugin.serialization") version "1.9.22"
}

group = "mx.com.blackengine"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test:1.9.23")

    implementation("com.github.0xshamil:java-xid:1.0.0")
    implementation("com.github.ajalt.clikt:clikt:4.3.0")
    implementation("com.github.f4b6a3:uuid-creator:5.3.7")
    implementation("com.github.xmlet:htmlflow:4.5")
    implementation("com.zaxxer:HikariCP:5.1.0")
    implementation("io.vertx:vertx-web:4.5.7")
    implementation("io.vertx:vertx-web-templ-pebble:4.5.7")
    implementation("org.jetbrains.kotlinx:kotlinx-html:0.11.0")
    implementation("org.postgresql:postgresql:42.7.3")
    implementation("com.google.guava:guava:33.1.0-jre")

    implementation("org.jetbrains.exposed:exposed-core:0.49.0")
    implementation("org.jetbrains.exposed:exposed-crypt:0.49.0")
    implementation("org.jetbrains.exposed:exposed-dao:0.49.0")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.49.0")
    implementation("org.jetbrains.exposed:exposed-json:0.49.0")
    implementation("org.jetbrains.exposed:exposed-kotlin-datetime:0.49.0")
    implementation("org.jetbrains.exposed:exposed-money:0.49.0")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}