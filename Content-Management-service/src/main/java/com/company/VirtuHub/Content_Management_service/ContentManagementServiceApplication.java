package com.company.VirtuHub.Content_Management_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ContentManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContentManagementServiceApplication.class, args);
	}

}
