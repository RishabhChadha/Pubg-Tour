package com.stackroute.favouriteservice.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.favouriteservice.domain.Match;
import com.stackroute.favouriteservice.exception.MatchAlreadyExistsException;
import com.stackroute.favouriteservice.exception.MatchNotFoundException;
import com.stackroute.favouriteservice.service.MatchService;

import io.jsonwebtoken.Jwts;



@CrossOrigin
@RestController
@RequestMapping("/api/v1/match")
public class MatchController {

	private MatchService MatchService;
	

	public MatchController(final MatchService MatchService) {
		this.MatchService=MatchService;
		
	}
	
    @PostMapping("")
    public ResponseEntity<?> saveMatch(@RequestBody Match match, HttpServletRequest request, HttpServletResponse response) {
    	ResponseEntity<?> responseEntity;
		
		final String authHeader = request.getHeader("authorization");
		final String token = authHeader.substring(7);
		String userId = Jwts.parser().setSigningKey("securitykey").parseClaimsJws(token).getBody().getSubject();
		try{
			match.setUserId(userId);
			MatchService.saveMatch(match);
			responseEntity = new ResponseEntity<Match>(match,HttpStatus.CREATED);
		}
		catch (MatchAlreadyExistsException e) {
			// TODO: handle exception
			responseEntity = new ResponseEntity<String>("{ \"message\": \"" +e.getMessage()+"\"}",HttpStatus.CONFLICT);
		}
		return responseEntity;
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateMatch(@PathVariable("id") final int id,@RequestBody final Match Match){
		ResponseEntity<?> responseEntity;
		try{
			final Match fetchedMatch = MatchService.updateMatch(Match);
			
			responseEntity=new ResponseEntity<Match>(fetchedMatch,HttpStatus.OK);
			
		}catch(MatchNotFoundException e){
			
			responseEntity=new ResponseEntity<String>("message: "+e.getMessage(),HttpStatus.CONFLICT);
		}
		return responseEntity;
	}
	
	@DeleteMapping(value = "/{userId}/{id}")
	public ResponseEntity<?> deleteMatchById(@PathVariable("userId") final String userId, @PathVariable("id") final String id){
		ResponseEntity<?> responseEntity;
		try {
			
			System.out.println(userId+" "+id);
			
			MatchService.deleteMatchById(id,userId);
		} catch (MatchNotFoundException e) {
			responseEntity = new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
		responseEntity = new ResponseEntity<String>("Match deleted successfully",HttpStatus.OK);
		return responseEntity;
	} 
	
	

	
	
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<?> fetchedMatchById(@PathVariable("id") final int id){
		ResponseEntity<?> responseEntity;
		try{
			final Match fetchedMatch = MatchService.getMatchById(id);
			
			responseEntity=new ResponseEntity<Match>(fetchedMatch,HttpStatus.OK);
			
		}catch(MatchNotFoundException e){
			
			responseEntity=new ResponseEntity<String>("message: "+e.getMessage(),HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}
	
	
    @GetMapping("")
    public ResponseEntity<List<Match>> getAllMatchs(final HttpServletRequest req, HttpServletResponse response) {
          final HttpServletRequest request=(HttpServletRequest) req;

		final String authHeader= request.getHeader("authorization");
		final String token = authHeader.substring(7);
		String userId= Jwts.parser().setSigningKey("securitykey").parseClaimsJws(token).getBody().getSubject();
		System.out.println("userId::"+userId);
		return new ResponseEntity<List<Match>>(MatchService.getMyMatches(userId),HttpStatus.OK);
	}
	
	

}
