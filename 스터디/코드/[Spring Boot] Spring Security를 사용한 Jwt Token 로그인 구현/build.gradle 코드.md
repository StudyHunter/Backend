```
plugins {
	id 'java'
	id 'org.springframework.boot' version '3.2.2'
	id 'io.spring.dependency-management' version '1.1.4'
}

group = 'com.example'
version = '0.0.1-SNAPSHOT'

java {
	sourceCompatibility = '17'
}

configurations {
	compileOnly {
		extendsFrom annotationProcessor
	}
}

repositories {
	mavenCentral()
}

dependencies {

	implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
	implementation 'org.springframework.boot:spring-boot-starter-web'
	testImplementation 'org.springframework.boot:spring-boot-starter-test'
	implementation 'com.github.gavlyukovskiy:p6spy-spring-boot-starter:1.9.0'

    //json
    implementation 'com.google.code.gson:gson:2.10.1'

    // https://mvnrepository.com/artifact/com.auth0/java-jwt
	implementation group: 'com.auth0', name: 'java-jwt', version: '4.4.0'
	//spring security
	implementation 'org.springframework.boot:spring-boot-starter-security'
	testImplementation 'org.springframework.security:spring-security-test'

	// lombok
	compileOnly 'org.projectlombok:lombok' // 롬복 라이브러리를 컴파일 시에만 의존성으로 추가
	annotationProcessor 'org.projectlombok:lombok' // 롬복 어노테이션 프로세서 추가
	//test 롬복 사용
	testCompileOnly 'org.projectlombok:lombok'
	testAnnotationProcessor 'org.projectlombok:lombok'

	// mysql
	runtimeOnly 'com.mysql:mysql-connector-j'

	//websocket
	implementation 'org.springframework.boot:spring-boot-starter-websocket'
	implementation 'org.webjars:sockjs-client:1.1.2'
	implementation 'org.webjars:stomp-websocket:2.3.3-1'

	//Querydsl 추가
	implementation 'com.querydsl:querydsl-jpa:5.0.0:jakarta'
	annotationProcessor "com.querydsl:querydsl-apt:${dependencyManagement.importedProperties['querydsl.version']}:jakarta"
	annotationProcessor "jakarta.annotation:jakarta.annotation-api"
	annotationProcessor "jakarta.persistence:jakarta.persistence-api"


	//view
	implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'
	implementation 'org.springframework.boot:spring-boot-starter-freemarker'
	implementation 'org.springframework.boot:spring-boot-devtools'
	implementation 'org.webjars.bower:bootstrap:4.3.1'
	implementation 'org.webjars.bower:vue:2.5.16'
	implementation 'org.webjars.bower:axios:0.17.1'
	implementation 'com.google.code.gson:gson:2.8.0'
}

/**
 * QueryDSL Build Options
 */
def querydslDir = "src/main/generated"

sourceSets {
	main.java.srcDirs += [ querydslDir ]
}

tasks.withType(JavaCompile) {
	options.getGeneratedSourceOutputDirectory().set(file(querydslDir))
}

clean.doLast {
	file(querydslDir).deleteDir()
}

/**
 *  QueryDSL Build Options 끝부분
*/

tasks.named('test') {
	useJUnitPlatform()
}


compileJava {
	options.compilerArgs += ["-parameters"]
}


clean {
	delete file('src/main/generated')
}
```
