import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.5.2"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    war
    kotlin("jvm") version "1.5.20"
    kotlin("plugin.spring") version "1.5.20"
}

val profile = project.properties["profile"] ?: "local"

group = "io.orbeon.wrapper"
version = "0.0.1-$profile"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.apache.tomcat.embed:tomcat-embed-jasper")
    implementation("javax.servlet:jstl")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("com.google.code.gson:gson")
    implementation(files("src/lib/orbeon-embedding.jar"))
    implementation(files("src/lib/slf4j-api-1.7.30.jar"))
    implementation("mysql:mysql-connector-java")
    providedRuntime("org.springframework.boot:spring-boot-starter-tomcat")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

allOpen {
    annotation("io.orbeon.wrapper.annotations.Open")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.processResources {
    duplicatesStrategy = DuplicatesStrategy.INCLUDE

    from( "${project.rootDir}/src/main/resources") {
        filesMatching("application.properties") {
            expand("profile" to profile)
        }
    }
}
