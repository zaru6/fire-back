package com.evilapp.fire;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.evilapp.fire")
@SpringBootApplication
public class FireApplication {

	public static void main(String[] args) {
		SpringApplication.run(FireApplication.class, args);
	}

}
