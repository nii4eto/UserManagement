package com.westernacher.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.westernacher.dto.UserDto;
import com.westernacher.entity.UserEntity;
import com.westernacher.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public void saveUser(UserDto userDto) {
		UserEntity user = UserTransformationService.trasformUserDtoToUser(userDto);
		userRepository.save(user);
	}

	@Override
	public UserDto findByEmail(String email) {
		UserEntity user = userRepository.findByEmail(email);
		UserDto userDto = prepareUserDto(user);

		return userDto;
	}

	@Override
	public UserDto findById(Long id) {
		UserEntity user = userRepository.findOne(id);
		UserDto userDto = prepareUserDto(user);
		return userDto;
	}

	@Override
	public void deleteUser(Long id) {
		userRepository.delete(id);
	}

	@Override
	public void updateUser(UserDto userDto) {
		UserEntity user = UserTransformationService.trasformUserDtoToUser(userDto);
		userRepository.updateUser(user.getId(), user.getFirstName(), user.getLastName(), user.getDateOfBirth());
	}

	@Override
	public List<UserDto> findAllUsers() {
		List<UserEntity> allUsersEntities = userRepository.findAll();

		return allUsersEntities.stream()
				.map(user -> UserTransformationService.transformUserEntityToUserDto(user))
				.collect(Collectors.toList());
	}

	private UserDto prepareUserDto(UserEntity user) {
		UserDto userDto = (user != null) ? UserTransformationService.transformUserEntityToUserDto(user) : null;
		return userDto;
	}
}
