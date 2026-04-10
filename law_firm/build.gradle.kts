plugins {
    kotlin("plugin.jpa") version "2.1.10"
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
}

tasks.test {
    useJUnitPlatform()
}