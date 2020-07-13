package com.example.demo;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.util.ParamUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
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
	public ResponseEntity logout(@RequestBody String in, HttpServletRequest req) {
		log.info("body in: {}", in);
		Enumeration<String> headerNames = req.getHeaderNames();

		while (headerNames.hasMoreElements()) {

			String headerName = headerNames.nextElement();
			log.info("header name:{}", headerName);

			Enumeration<String> headers = req.getHeaders(headerName);
			while (headers.hasMoreElements()) {
				String headerValue = headers.nextElement();
				log.info("header value: {}", headerValue);
			}

		}
		return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
	}

	@PostMapping("/auth/token")
	public Object getToken(@RequestBody String in) {
		log.info("body {}", in);
		JSONObject out = new JSONObject();
		out.put("accessToken", "7512eb3feb5249eca5ddd742fedddd39");
		out.put("expires", 1800);
		return out;
	}

	@PostMapping("/parseMap")
	public Object testParseMap(@RequestBody String in) {
		Map<String, Object> retObj = ParamUtil.convertJsonStrToMap(in);

//		Map<String, String> strMap = ParamUtil.convertJsonStrToMap(in);


		return retObj;
	}
}