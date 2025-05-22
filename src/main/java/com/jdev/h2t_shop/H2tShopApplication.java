package com.jdev.h2t_shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class H2tShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(H2tShopApplication.class, args);
	}

}
