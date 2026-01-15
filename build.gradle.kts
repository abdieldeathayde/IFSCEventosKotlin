buildscript {
    dependencies {
        classpath("com.google.gms:google-services:4.4.4")
    }
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.


// Arquivo: build.gradle.kts (Raiz do Projeto)
plugins {
    id("com.android.application") version "8.12.3" apply false
    id("org.jetbrains.kotlin.android") version "2.3.0" apply false // Use a versão 2.0.x ou superior

    // ADICIONE ESTA LINHA:
    id("org.jetbrains.kotlin.plugin.compose") version "2.3.0" apply false
    id("com.google.gms.google-services") version "4.4.4" apply false
}