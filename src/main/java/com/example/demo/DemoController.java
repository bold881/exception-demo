package com.example.demo;

import com.example.demo.util.ParamUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

	@PostMapping("/auth/logout")
	public ResponseEntity logout(@RequestBody String in) {
		log.info("body in: {}", in);
		return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
	}

	@PostMapping("/parseMap")
	public Object testParseMap(@RequestBody String in) {
		Map<String, Object> retObj = ParamUtil.convertJsonStrToMap(in);

//		Map<String, String> strMap = ParamUtil.convertJsonStrToMap(in);


		return retObj;
	}
}