package com.blurdel.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

	
	@GetMapping
	public String hello(@RequestParam(defaultValue = "World!") String name) {
		String greeting = String.format("Hello %s", name);
		return greeting;
	}
	
}
