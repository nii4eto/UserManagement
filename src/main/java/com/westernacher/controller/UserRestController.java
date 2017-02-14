package com.westernacher.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
	public List<UserDto> getAllUsers() {
		return userService.findAllUsers();
	}

	@RequestMapping(value = "/saveUser", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> saveUser(@RequestBody UserDto userDto) {
		if (userService.findByEmail(userDto.getEmail()) != null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		userService.saveUser(userDto);
		return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteUser(@PathVariable Long id) {
		try {
			userService.deleteUser(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch(EmptyResultDataAccessException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	public UserDto getUserForEdit(@PathVariable Long id) {
		return userService.findById(id);
	}
	
	@RequestMapping(value = "/editUser", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateUser(@RequestBody UserDto userDto) {
		try {
			userService.updateUser(userDto);
			return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
