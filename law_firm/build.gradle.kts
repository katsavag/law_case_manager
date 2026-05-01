plugins {
    kotlin("plugin.jpa") version "2.1.10"
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
}

tasks.named<Jar>("jar") {
    enabled = true
}

tasks.test {
    useJUnitPlatform()
}