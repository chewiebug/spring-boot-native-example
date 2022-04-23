package com.github.chewiebug.spring.boot.nativ;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootNativeExampleApplication {

	public static void main(String[] args) {
		System.out.println("maxMem=" + Runtime.getRuntime().maxMemory() + ", " + Runtime.getRuntime().maxMemory() / 1024 / 1024 + "MB");
		System.out.println("availableProcessors=" + Runtime.getRuntime().availableProcessors());

		SpringApplication.run(SpringBootNativeExampleApplication.class, args);
	}

}
