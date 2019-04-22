package com.stackroute.favouriteservice.service;

import java.util.List;

import com.stackroute.favouriteservice.domain.Match;
import com.stackroute.favouriteservice.exception.MatchAlreadyExistsException;
import com.stackroute.favouriteservice.exception.MatchNotFoundException;



public interface MatchService {

	
	boolean saveMatch(Match match) throws MatchAlreadyExistsException;
	
	Match updateMatch(Match match) throws MatchNotFoundException;
	
	boolean deleteMatchById(String userId, String Id) throws MatchNotFoundException;
	
	Match getMatchById(int Id) throws MatchNotFoundException;
	
	
	List<Match> getMyMatches(String userId) ;
	
}
