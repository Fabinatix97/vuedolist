package com.fabinatix.vuedoapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import lombok.extern.java.Log;

@SpringBootApplication
@Log
@EnableScheduling
public class VuedoapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(VuedoapiApplication.class, args);
	}

}
