package com.stackroute.favouriteservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.favouriteservice.domain.Match;






@Repository
public interface MatchRepository extends JpaRepository<Match, Integer> {

	//@Query
	List<Match> findByUserId(String userId);
	
	Match findByIdAndUserId(String id,String userId);
	
	
}
