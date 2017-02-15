package service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import com.westernacher.dto.UserDto;
import com.westernacher.service.UserService;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	@InjectMocks
    @Autowired
    private UserService userService;
 
    @Mock
    private UserDto userDto;
    
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void createUser() {
    	prepareUser("nia@gmail.com");
    	userService.saveUser(userDto);
    	
    	UserDto newUserDto = userService.findByEmail("nia@gmail.com");
    	

    }
    
    private void prepareUser(String email) {
		userDto.setFirstName("Nia");
		userDto.setLastName("Doncheva");
		userDto.setDateOfBirth("1992-07-21");
		userDto.setEmail(email);
	}
}
