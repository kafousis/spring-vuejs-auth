package com.github.kafousis.prototype;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class PrototypeBackendApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(PrototypeBackendApplication.class, args);
	}

}
