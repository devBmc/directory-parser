package com.company.directoryparser;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
/**
 * This is the main entry point of the application
 * Config file can be find in src/main/resource/application.properties
 * @author raghosh
 *
 */
public class DirectoryParserApplication {
	private static final Logger logger = LoggerFactory.getLogger(DirectoryParserApplication.class);
	public static void main(String[] args) {
		logger.info("Starting logging for App ..");
		SpringApplication.run(DirectoryParserApplication.class, args);
	}

}
