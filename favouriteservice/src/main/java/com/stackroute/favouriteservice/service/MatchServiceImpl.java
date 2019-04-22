package com.stackroute.favouriteservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.favouriteservice.domain.Match;
import com.stackroute.favouriteservice.exception.MatchAlreadyExistsException;
import com.stackroute.favouriteservice.exception.MatchNotFoundException;
import com.stackroute.favouriteservice.repository.MatchRepository;

@Service
public class MatchServiceImpl implements MatchService {

	public final transient MatchRepository matchRepo;

	@Autowired
	public MatchServiceImpl(MatchRepository matchRepo) {
		super();
		this.matchRepo = matchRepo;
	}

	@Override
	public boolean saveMatch(final Match match) throws MatchAlreadyExistsException {
		// other method
		final Optional<Match> object = matchRepo.findById(match.getFavId());
		if (object.isPresent()) {
			throw new MatchAlreadyExistsException("could not save Match");
		}
		matchRepo.save(match);
		return true;
	}

	@Override
	public Match updateMatch(final Match updateMatch) throws MatchNotFoundException {
		// TODO Auto-generated method stub
		final Match match = matchRepo.findById(updateMatch.getFavId()).orElse(null);

		if (match == null) {
			throw new MatchNotFoundException("Couldn't update Match . Match not found");
		}
		match.setTitleId(updateMatch.getTitleId());
		matchRepo.save(match);
		return match;
	}

	@Override
	public boolean deleteMatchById(String id,String userId) throws MatchNotFoundException {
		// TODO Auto-generated method stub
		System.out.println(id+" "+userId);
		final Match match = matchRepo.findByIdAndUserId(id,userId);
		System.out.println(match);
		System.out.println("hellos");
		

		if (match.getCreatedAt() == null) {
			throw new MatchNotFoundException("Match cannot be deleted . Match not found");
		}
		matchRepo.delete(match);
		return true;
	}

	@Override
	public Match getMatchById(int id) throws MatchNotFoundException {
		// TODO Auto-generated method stub
		final Match Match = matchRepo.findById(id).orElse(null);
		if (Match == null) {
			throw new MatchNotFoundException("Match could not be found");
		}

		return Match;
	}

	@Override
	public List<Match> getMyMatches(String userId) {
		// TODO Auto-generated method stub
		System.out.println(userId);

		return matchRepo.findByUserId(userId);
	}

}
