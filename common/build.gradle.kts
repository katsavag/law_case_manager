plugins {
    kotlin("jvm")
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-reflect")
}

tasks.named<Jar>("jar") {
    enabled = true
}

tasks.test {
    useJUnitPlatform()
}
