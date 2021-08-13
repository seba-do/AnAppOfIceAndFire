// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {

    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(Libraries.Classpath.gradle)
        classpath(Libraries.Classpath.kotlinGradle)
        classpath(Libraries.Classpath.hilt)

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {

    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("clean",Delete::class){
    delete(rootProject.buildDir)
}