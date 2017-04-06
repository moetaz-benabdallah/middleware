package com.efacil.middleware;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

/**
 * @author Moatez Ben Abdallah
 **/
@Controller
@SpringBootApplication
@Configuration
@ComponentScan(basePackages = "com.efacil")
@EnableAutoConfiguration
public class EFacilMiddlewareApplication {

	@RequestMapping("/")
	@ResponseBody
	String Health() {
		return "done!";
	}

	public static void main(String[] args) {
		SpringApplication.run(EFacilMiddlewareApplication.class, args);
	}
}
