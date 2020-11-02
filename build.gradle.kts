import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.10"
    application
}
group = "Gradle / Gradle Kotlin DSL (Group B)"
version = "Version 1.0 Gradle"

dependencies {
    api("junit:junit:4.13")
    implementation("junit:junit:4.13")
    testImplementation("junit:junit:4.13")
}

configurations {
    implementation {
        resolutionStrategy.failOnVersionConflict()
    }
}

sourceSets {
    main {
        java.srcDir("src/core/java")
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

repositories {
    mavenCentral()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}
application {
    mainClassName = "MainKt"
}

//To use the terminal to execute this Task
//remember to change the path of the terminal to the root one
//execute it then with STRG + Enter
//otherwise you can run task with the "Gradle" interface on the right side from your IDE(Intellij)

tasks.register("helloworld") {
    doLast {
        println("Hello world!")
    }
}
tasks.register("upper") {
    doLast {
        val someString = "mY_nAmE"
        println("Original: $someString")
        println("Upper case: ${someString.toUpperCase()}")
    }
}

tasks.register("count") {
    doLast {
        repeat(4) { print("$it ") }
    }
}

tasks.register("intro") {
    dependsOn("hello") //Use Hello Task
    doLast {
        println("I'm Gradle")
    }
}

repeat(4) { counter ->
    tasks.register("task$counter") {
        doLast {
            println("I'm task number $counter")
        }
    }
}

//Create Task with an order, every doLast will be set on the end of the tasks list in this task hello
val hello by tasks.registering {
    doLast {
        println("Hello Earth")
    }
}
hello {
    doFirst {
        println("Hello Venus")
    }
}
hello {
    doLast {
        println("Hello Mars")
    }
}
hello {
    doLast {
        println("Hello Jupiter")
    }
}
