package com.example.darkonnen.demo.UdemyDemoApp1.rest;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunnyRestController {
	
	@GetMapping("/")
	public String sayHello() {
		return "Hello World " + LocalDateTime.now();
	}
	

}
