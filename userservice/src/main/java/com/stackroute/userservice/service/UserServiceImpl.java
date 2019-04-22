package com.stackroute.userservice.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.stackroute.userservice.domain.User;
import com.stackroute.userservice.exception.UserAlreadyExistsException;
import com.stackroute.userservice.exception.UserNotFoundException;
import com.stackroute.userservice.repository.UserRepository;




@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepo;
	
	
	public UserServiceImpl(UserRepository userRepo) {
		super();
		this.userRepo=userRepo;
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean saveUser(User user) throws UserAlreadyExistsException {
		Optional<User> user1 = userRepo.findById(user.getUserId());
		
		if(user1.isPresent()){
			throw new UserAlreadyExistsException("usrer with id already exists");
		}
		userRepo.save(user);
		return true;
	}

	@Override
	public User findByUserIdAndPassword(String userId, String password) throws UserNotFoundException {
		
		User user = userRepo.findByUserIdAndPassword(userId, password);
		if(user == null){
			throw new UserNotFoundException("userId and password mismatch");
		}
		return user;
		
	}

}
