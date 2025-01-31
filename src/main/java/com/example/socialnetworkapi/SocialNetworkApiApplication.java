package com.example.socialnetworkapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.config.EnableNeo4jAuditing;

@SpringBootApplication
@EnableNeo4jAuditing
public class SocialNetworkApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocialNetworkApiApplication.class, args);
	}

}
