package com.yusufcakal.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@ComponentScan
@SpringBootApplication
public class EcommerceApplication extends WebMvcConfigurerAdapter{

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}

}
