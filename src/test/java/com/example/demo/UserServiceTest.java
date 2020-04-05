package com.example.demo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest

public class UserServiceTest {

	@Autowired
	private UserService userService;

	@Test
	public void addUser() throws Exception {
		Assert.assertEquals(Integer.valueOf(1), userService.addUser("zhihao.miao"));
		Assert.assertEquals(Integer.valueOf(0), userService.addUser(null));
	}
}