package com.booky.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class BookyApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(BookyApplication.class, args);
	}

}
