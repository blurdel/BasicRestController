package com.blurdel.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class BasicRestControllerApplication implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(BasicRestControllerApplication.class);
			
	@Autowired
    private Environment env;
	
	
	public static void main(String[] args) {
		SpringApplication.run(BasicRestControllerApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		logger.info("JAVA_HOME: {}", env.getProperty("JAVA_HOME"));
		logger.info("SPRING_PROFILES_ACTIVE: {}", env.getProperty("SPRING_PROFILES_ACTIVE"));
	}

}
