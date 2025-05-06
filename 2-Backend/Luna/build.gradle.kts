plugins {
	java
	id("org.springframework.boot") version "3.4.2"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.lvnam0801"
version = "0.0.1"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	implementation("org.springframework.boot:spring-boot-starter-data-jdbc:2.1.8.RELEASE")
	implementation("mysql:mysql-connector-java:8.0.33")
	implementation("org.projectlombok:lombok:1.18.36")
	compileOnly("org.projectlombok:lombok:1.18.36")
	annotationProcessor("org.projectlombok:lombok:1.18.36")

	implementation ("com.google.code.gson:gson:2.10.1")
	implementation("io.jsonwebtoken:jjwt-api:0.11.5")
	runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.5")
	runtimeOnly("io.jsonwebtoken:jjwt-gson:0.11.5") // You prefer Gson

	implementation("org.springframework.boot:spring-boot-starter-security")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
