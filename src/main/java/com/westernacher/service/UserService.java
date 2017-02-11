package com.westernacher.service;

import java.util.List;

import com.westernacher.dto.UserDto;

public interface UserService {
	void saveUser(UserDto userDto);
	
	UserDto findByEmail(String email);
	
	UserDto findById(Long id);
	
	void deleteUser(Long id);
	
	List<UserDto> findAllUsers();
	
	void updateUser(UserDto userDto);
}