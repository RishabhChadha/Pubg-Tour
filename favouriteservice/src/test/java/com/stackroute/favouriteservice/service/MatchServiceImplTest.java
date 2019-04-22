package com.stackroute.favouriteservice.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;

import com.stackroute.favouriteservice.domain.Match;
import com.stackroute.favouriteservice.exception.MatchAlreadyExistsException;
import com.stackroute.favouriteservice.exception.MatchNotFoundException;
import com.stackroute.favouriteservice.repository.MatchRepository;
public class MatchServiceImplTest {

	@Mock
	private transient MatchRepository matchRepo;

	@Mock
	private transient Match match;

	@InjectMocks
	private transient MatchServiceImpl matchServiceImpl;

	transient Optional<Match> options;

	@Before
	public void setUpMock() {
		MockitoAnnotations.initMocks(this);
		match = new Match(2, "123", "1234", "duo", "india", "erangal", "new");
		options = Optional.of(match);
	}

	/**
	 * 
	 */
	@Test
	public void testMockCreation() {
		assertNotNull("Match service implementation failed use @injectMocks  on matcherviceImpl", matchServiceImpl);
	}

	@Test
	public void testSavematchSuccess() throws MatchAlreadyExistsException {
		when(matchRepo.save(match)).thenReturn(match);
		final boolean flag = matchServiceImpl.saveMatch(match);
		assertTrue("saving match failed, the call", flag);
		verify(matchRepo, times(1)).save(match);
		verify(matchRepo, times(1)).findById(match.getFavId());

	}

	@Test(expected = MatchAlreadyExistsException.class)
	public void testSavematchFailure() throws MatchAlreadyExistsException {
		when(matchRepo.findById(2)).thenReturn(options);
		when(matchRepo.save(match)).thenReturn(match);
		final boolean flag = matchServiceImpl.saveMatch(match);
		assertFalse("saving match failed", flag);

		verify(matchRepo, times(1)).findById(match.getFavId());

	}

	@Test
	public void testupdatematch() throws MatchNotFoundException {
		when(matchRepo.findById(2)).thenReturn(options);
		when(matchRepo.save(match)).thenReturn(match);
		match.setMapName("sanhok");
		final Match match1 = matchServiceImpl.updateMatch(match);

		assertEquals("saving match failed", "sanhok", match1.getMapName());
		verify(matchRepo, times(1)).save(match);
		verify(matchRepo, times(1)).findById(match.getFavId());

	}

	@Test
	public void testDeletematchById() throws MatchNotFoundException {
		when(matchRepo.findByIdAndUserId("123", "1234")).thenReturn(match);
		doNothing().when(matchRepo).delete(match);

		final boolean flag = matchServiceImpl.deleteMatchById("123", "1234");

		assertTrue("deleting match failed", flag);
		verify(matchRepo, times(1)).delete(match);
		verify(matchRepo, times(1)).findByIdAndUserId(match.getId(),match.getUserId());

	}

	
	@Test
	public void getmatchById() throws MatchNotFoundException {
		when(matchRepo.findById(2)).thenReturn(options);
		final Match match1 = matchServiceImpl.getMatchById(2);
		assertEquals("Getting match by This id failed", match1, match1);
		verify(matchRepo, times(1)).findById(match.getFavId());

	}

	
	@Test
	public void testGetMyMatchs() {
		List<Match> matchList = new ArrayList<>(1);
		when(matchRepo.findByUserId("1234")).thenReturn(matchList);
		final List<Match> match1 = matchServiceImpl.getMyMatches("1234");
		assertEquals(matchList, match1);
		verify(matchRepo, times(1)).findByUserId("1234");

	}

}
