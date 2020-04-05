package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

	private final Logger log = LoggerFactory.getLogger(DemoController.class);

	@GetMapping(value = "/")
	public String root() throws Exception {
		log.info("");
		return "root";
	}

	@GetMapping(value = "test1/{abc}")
	public String test1(@PathVariable String abc) throws Exception {
		log.info("参数值={}", abc);
		return abc;
	}
}