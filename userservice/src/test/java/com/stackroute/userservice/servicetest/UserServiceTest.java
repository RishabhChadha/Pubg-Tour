package com.stackroute.userservice.servicetest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.stackroute.userservice.domain.User;
import com.stackroute.userservice.exception.UserAlreadyExistsException;
import com.stackroute.userservice.exception.UserNotFoundException;
import com.stackroute.userservice.repository.UserRepository;
import com.stackroute.userservice.service.UserServiceImpl;




public class UserServiceTest {

	@Mock
	private UserRepository userRepo;
	
	private User user;
	
	@InjectMocks
	private UserServiceImpl userServiceImpl;
	
	
	Optional<User> options;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		user = new User("abc","abc","abc","12345678",new Date());
		options=Optional.of(user);
	}
	
	@Test
	public void testSaveUserSuccess() throws UserAlreadyExistsException{
		when(userRepo.save(user)).thenReturn(user);
		boolean flag = userServiceImpl.saveUser(user);
		assertEquals("can not regsiter user",true,flag);
		verify(userRepo, times(1)).save(user);
		
	}
	@Test(expected=UserAlreadyExistsException.class)
	public void testSaveUserFailure() throws UserAlreadyExistsException,UserNotFoundException{
		when(userRepo.findById(user.getUserId())).thenReturn(options);
		when(userRepo.save(user)).thenReturn(user);
		boolean flag = userServiceImpl.saveUser(user);
		
		
	}
	
	@Test
	public void testValidDataSuccess() throws UserNotFoundException{
		when(userRepo.findByUserIdAndPassword(user.getUserId(), user.getPassword())).thenReturn(user);
		User userResult = userServiceImpl.findByUserIdAndPassword(user.getUserId(), user.getPassword());
		assertNotNull(userResult);
		assertEquals("abc",userResult.getUserId());
		verify(userRepo,times(1)).findByUserIdAndPassword(user.getUserId(), user.getPassword());
		
	}
	
	@Test(expected=UserNotFoundException.class)
	public void testValidateFailure() throws UserNotFoundException{
		when(userRepo.findById("abc")).thenReturn(null);
		userServiceImpl.findByUserIdAndPassword(user.getUserId(), user.getPassword());
	}

}
