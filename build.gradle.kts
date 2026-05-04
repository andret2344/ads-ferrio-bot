plugins {
	java
	idea
	application
}

repositories {
	mavenCentral()
}

dependencies {
	implementation(libs.jda)
	implementation(libs.gson)
	// logging
	implementation(libs.slf4j.api)
	implementation(libs.log4j.slf4j.impl)
	implementation(libs.log4j.core)
	// testing
	testImplementation(platform(libs.junit.bom))
	testImplementation(libs.junit.jupiter)
	testRuntimeOnly(libs.junit.platform.launcher)
}

application {
	mainClass.set("eu.andret.ads.ferrio.Ferrio")
}

tasks {
	compileJava {
		sourceCompatibility = JavaVersion.VERSION_25.toString()
		targetCompatibility = JavaVersion.VERSION_25.toString()
	}

	test {
		useJUnitPlatform()
	}

	jar {
		duplicatesStrategy = DuplicatesStrategy.WARN

		from({
			configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) }
		})

		manifest {
			attributes["Main-Class"] = application.mainClass.get()
		}
	}
}
