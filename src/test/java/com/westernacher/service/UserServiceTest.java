package com.westernacher.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.SpringBootConfiguration;

import com.westernacher.dto.UserDto;
import com.westernacher.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
@SpringBootConfiguration
public class UserServiceTest {
	
	private static final String EMAIL = "nia@gmail.com";

	@InjectMocks
    private UserServiceImpl userServiceImpl;
	
	@Mock
	private UserRepository userRepo;

    private UserDto userDto = new UserDto();
    
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void testSaveUserSuccess() {
    	prepareUser(EMAIL);
    	userServiceImpl.saveUser(userDto);
    	
    	UserDto newUserDto = userServiceImpl.findByEmail(EMAIL);
    	
    	if(newUserDto != null){
    		assertNotNull(newUserDto);
    		assertEquals(newUserDto.getEmail(), userDto.getEmail());
    	} else {
    		fail();
    	}	
    }
    
    @Test
    public void testUpdateUser() {
    	prepareUser(EMAIL);
    	userServiceImpl.saveUser(userDto);
    	
    	UserDto userForUpdate = userServiceImpl.findByEmail(EMAIL);
    	if(userForUpdate == null) {
    		fail("User doesn't exist!");
    	}
    }
    
    private void prepareUser(String email) {
		userDto.setFirstName("Nia");
		userDto.setLastName("Doncheva");
		userDto.setDateOfBirth("1992-07-21");
		userDto.setEmail(email);
	}
}
