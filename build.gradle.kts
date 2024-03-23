plugins {
    kotlin("jvm") version "1.9.22"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test:1.9.22")

    implementation("com.github.ajalt.clikt:clikt:4.2.2")
    implementation("io.vertx:vertx-web:4.5.5")
    implementation("com.github.xmlet:htmlflow:4.4")
    implementation("org.jetbrains.kotlinx:kotlinx-html:0.11.0")
    implementation("com.github.0xshamil:java-xid:1.0.0")
    implementation("org.postgresql:postgresql:42.7.3")
    implementation("com.zaxxer:HikariCP:5.1.0")

    implementation("org.jetbrains.exposed:exposed-core:0.48.0")
    implementation("org.jetbrains.exposed:exposed-crypt:0.48.0")
    implementation("org.jetbrains.exposed:exposed-dao:0.48.0")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.48.0")
    implementation("org.jetbrains.exposed:exposed-json:0.48.0")
    implementation("org.jetbrains.exposed:exposed-kotlin-datetime:0.48.0")
    implementation("org.jetbrains.exposed:exposed-money:0.48.0")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}