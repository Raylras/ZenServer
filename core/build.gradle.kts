import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id ("antlr")
}

group = "raylras.intellizen.core"

dependencies {
    antlr("org.antlr:antlr4:4.13.1")
    implementation("org.antlr:antlr4-runtime:4.13.1")
    implementation("com.google.code.gson:gson:2.11.0")
    implementation("com.strumenta.kolasu:kolasu-core:1.5.60")
    implementation("com.strumenta.kolasu:kolasu-semantics:1.5.60")
}

sourceSets.main {
    java.srcDirs("src/main/gen/java")
}

tasks.named<AntlrTask>("generateGrammarSource") {
    maxHeapSize = "64m"
    arguments = listOf("-visitor", "-listener", "-long-messages", "-package", "raylras.intellizen.parser")
    outputDirectory = file("src/main/gen/java/raylras/intellizen/parser")
}

tasks.named<KotlinCompile>("compileKotlin") {
    dependsOn("generateGrammarSource")
}

tasks.named<Copy>("distDeps") {
    include("antlr4-runtime-*.jar")
    include("gson-*.jar")
}

tasks.named<Delete>("clean") {
    finalizedBy(tasks.named("cleanGrammarSource"))
}

tasks.register<Delete>("cleanGrammarSource") {
    group = "build"
    delete("src/main/gen")
}
