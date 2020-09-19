import org.jetbrains.kotlin.gradle.dsl.KotlinJvmCompile

plugins {
    java

    val kotlinVersion = "1.4.20"
    kotlin("jvm") version kotlinVersion
}

group = "org.example"
version = "0.1.0"

repositories {
    jcenter()
    mavenCentral()
}

tasks.withType(KotlinJvmCompile::class.java) {
    kotlinOptions.jvmTarget = "1.8"
}

dependencies {
    val miraiVersion = "2.0-M1"

    // 开发时使用 mirai-core-api，运行时提供 mirai-core
    api("net.mamoe:mirai-core-api:$miraiVersion")
    runtimeOnly("net.mamoe:mirai-core:$miraiVersion")

    // 可以简单地只添加 api("net.mamoe:mirai-core:2.0-M1")
}