package com.stackroute.userservice.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.stackroute.userservice.domain.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
@Service
public class JwtsecurityTokengeneratorImpl implements securityTokenGenerator {

	public JwtsecurityTokengeneratorImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Map<String, String> generateToken(User user) {
		String JwtToken= "";
		JwtToken = Jwts.builder().setSubject(user.getUserId()).setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS256, "securitykey").compact();
		Map<String, String> map= new HashMap<>();
		map.put("token" , JwtToken);
		map.put("message" , "user successfully logged in");
		
		return map;
	}

}
