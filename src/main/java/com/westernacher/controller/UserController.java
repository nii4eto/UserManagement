package com.westernacher.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

	@RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
	public String goHome() {
		return "index";
	}
	
	@RequestMapping(value = "/createUser", method = RequestMethod.GET)
	public String createUser() {
		return "createUser";
	}
}
