/*
Building a RESTful webService
Consuming a RESTful Web Service
Enabling Cross Origin Requests for a RESTful Web Service
 */

package com.example.demo;

import com.example.demo.consumingrest.Quote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
public class DemoApplication {

	// mvnw spring-boot:run
	// mvnw clean package
	// mvnw spring-boot:run -Dserver.port=9000 (doesn't work)

	/*
	Build an executable JAR

	1. build the JAR file
	mvnw clean package
	2. run the JAR file
	java -jar target/demo-0.0.1-SNAPSHOT.jar
	 */

	private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@GetMapping("/hello")
	public String hello(@RequestParam(value="name", defaultValue = "World") String name){
		return String.format("Hello %s!", name);
	}
}
