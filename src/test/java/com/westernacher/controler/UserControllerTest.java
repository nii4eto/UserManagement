package com.westernacher.controler;

import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.westernacher.controller.UserController;
import com.westernacher.service.UserService;

public class UserControllerTest {

	@Mock
	private UserService userService;
	
	@InjectMocks
	private UserController userController;
	
	private MockMvc mockMvc;
	
	@Before
    public void setup() {
 
        // Process mock annotations
        MockitoAnnotations.initMocks(this);
 
        // Setup Spring test in standalone mode
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
 
    }
}
