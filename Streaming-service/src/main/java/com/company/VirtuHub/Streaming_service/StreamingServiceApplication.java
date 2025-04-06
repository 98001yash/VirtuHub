package com.company.VirtuHub.Streaming_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class StreamingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StreamingServiceApplication.class, args);
	}

}
