package com.westernacher.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.westernacher.dto.UserDto;
import com.westernacher.service.UserService;

@RestController
public class UserRestController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public List<UserDto> getAllUsers(Model model) {
		return userService.findAllUsers();
	}

}
