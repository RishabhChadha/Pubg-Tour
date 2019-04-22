package com.stackroute.userservice.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.stackroute.userservice.domain.User;
import com.stackroute.userservice.service.UserService;
import com.stackroute.userservice.service.securityTokenGenerator;



@RestController
@EnableWebMvc
@RequestMapping("/user")
@CrossOrigin
public class UserController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private securityTokenGenerator tokenGenerator;
	
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody User user){
		
		try{
			userService.saveUser(user);
			return new ResponseEntity<String>("user registerd successfully", HttpStatus.CREATED);
			} catch (Exception e){
				return new ResponseEntity<String>("{ \"message\": \""+e.getMessage()+"\"}",HttpStatus.CONFLICT);
			}
	}

	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody User loginDetail){
	try{
		String userId=loginDetail.getUserId();
		String password=loginDetail.getPassword();
		
		if(userId==null || password == null) {
			throw new Exception("Username and password cannot be null");
		}
		User user=userService.findByUserIdAndPassword(userId, password);
		if(user==null){
			throw new Exception("user not found");
			
		}
		if(!password.equals(user.getPassword())){
			throw new Exception("invalid login credential");
		}
		
		Map<String, String> map= tokenGenerator.generateToken(user);
		return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
		
	}catch (Exception e) {
		return new ResponseEntity<String>("{ \"message\": \""+e.getMessage() + "\"}",HttpStatus.CONFLICT);
		// TODO: handle exception
	}
	
		
	}
	
	
	public UserController() {
		
	}

}
