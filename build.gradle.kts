plugins {
    id("java")
    id("application")
    id("org.openjfx.javafxplugin") version "0.1.0"
}

group = "fr"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(25))
    }
}

javafx {
    version = "23.0.1"
    modules("javafx.controls", "javafx.fxml")
}

application {
    mainClass.set("fr.pokeduel.Main")
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation(group = "com.fasterxml.jackson.core", name = "jackson-core", version = "2.15.0")
    implementation(group = "com.fasterxml.jackson.core", name = "jackson-annotations", version = "2.0.1")
    implementation(group = "com.fasterxml.jackson.core", name = "jackson-databind", version = "2.13.4.2")
}

tasks.test {
    useJUnitPlatform()
}