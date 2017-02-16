package com.westernacher.controler;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.google.gson.Gson;
import com.westernacher.controller.UserRestController;
import com.westernacher.dto.UserDto;
import com.westernacher.service.UserServiceImpl;

public class UserControllerTest {

	@Mock
	private UserServiceImpl userServiceImpl;

	@Mock
	private UserRestController userController;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);

		this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

	}

	@Test
	public void testListAllUsers() throws Exception {
		UserDto userDtoOne = prepareUser("test1@gmail.com");
		UserDto userDtoTwo = prepareUser("test2@gmail.com");

		when(userServiceImpl.findAllUsers()).thenReturn(Arrays.asList(userDtoOne, userDtoTwo));

		MvcResult andReturn = mockMvc.perform(get("/users")).andExpect(status().isOk()).andReturn();

		assertEquals(andReturn.getResponse().getStatus(), HttpStatus.OK.value());
	}

	@Test
	public void testSaveUser() throws Exception {
		UserDto userDto = prepareUser("test@gmail.com");
		Gson gson = new Gson();
		String json = gson.toJson(userDto);

		when(userServiceImpl.saveUser(any(UserDto.class))).thenReturn(userDto);

		MvcResult andReturn = mockMvc.perform(post("/saveUser").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk()).andReturn();

		assertEquals(andReturn.getResponse().getStatus(), HttpStatus.OK.value());
	}

	@Test
	public void testEditUser() throws Exception {
		UserDto userDto = prepareUser("test@gmail.com");

		Gson gson = new Gson();
		String json = gson.toJson(userDto);

		when(userServiceImpl.updateUser(any(UserDto.class))).thenReturn(userDto);

		MvcResult andReturn = mockMvc.perform(put("/editUser").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk()).andReturn();

		assertEquals(andReturn.getResponse().getStatus(), HttpStatus.OK.value());
	}

	private UserDto prepareUser(String email) {
		UserDto userDto = new UserDto();
		userDto.setFirstName("Nia");
		userDto.setLastName("Doncheva");
		userDto.setDateOfBirth("2017-01-01");
		userDto.setEmail(email);

		return userDto;
	}
}
