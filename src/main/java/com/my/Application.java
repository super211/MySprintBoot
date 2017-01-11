package com.my;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableCaching
//@ComponentScan("com.my")//it is not necessary when aspect in the same project
//@Import(MetricConfiguration.class)//it is not necessary when aspect in the same project
public class Application {

	public static void main(String[] args) {
		System.out.println("Starting My MicroService!");
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public MethodValidationPostProcessor methodValidationPostProcessor() {
		return new MethodValidationPostProcessor();
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
}