plugins {
    id("org.springframework.boot")
}

dependencies {
    implementation(project(":lawyer"))
    implementation(project(":client"))
    implementation(project(":common"))
    implementation("org.springframework.boot:spring-boot-starter-web")
}

tasks.getByName<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
    enabled = true
}

tasks.getByName<org.gradle.api.tasks.bundling.Jar>("jar") {
    enabled = false
}
