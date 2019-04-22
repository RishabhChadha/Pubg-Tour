package com.stackroute.favouriteservice.controllers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.util.ArrayList;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.favouriteservice.controller.MatchController;
import com.stackroute.favouriteservice.domain.Match;
import com.stackroute.favouriteservice.service.MatchService;

@RunWith(SpringRunner.class)
@WebMvcTest(MatchControllerTest.class)
public class MatchControllerTest {

	@Autowired
	private transient MockMvc mock;

	@MockBean
	private transient MatchService matchServices;

	@InjectMocks
	private MatchController matchController;

	private transient Match match;

	static List<Match> matches;

	@Before
	public void beforeStart() {
		MockitoAnnotations.initMocks(this);
		matches = new ArrayList<Match>();
		mock = MockMvcBuilders.standaloneSetup(matchController).build();
		match = new Match(1, "12", "1234", "duo", "india", "erangal", "new");
		matches.add(match);
		match = new Match(2, "123", "1234", "duo", "india", "erangal", "new");
		matches.add(match);
	}

	@Test
	public void testSaveNewMatchSuccess() throws Exception {
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMjM0IiwiaWF0IjoxNTUzMzM3MzU2fQ.zK0LSbjbcj2TtB742mkYCu94BqSbsaiwfXXHEB4VBbY";
		when(matchServices.saveMatch(match)).thenReturn(true);
		mock.perform(post("/api/v1/match").contentType(MediaType.APPLICATION_JSON)
				.header("authorization", "Bearer " + token).content(jsonToString(match)))
				.andExpect(status().isCreated());
		verify(matchServices, times(1)).saveMatch(Mockito.any(Match.class));
		verifyNoMoreInteractions(matchServices);

	}

	@Test
	public void testUpdateMovieSuccess() throws Exception {
		match.setGameMode("duo");
		when(matchServices.updateMatch(match)).thenReturn(matches.get(0));
		mock.perform(put("/api/v1/match/{id}", match.getId()).contentType(MediaType.APPLICATION_JSON)
				.content(jsonToString(match))).andExpect(status().isOk());
		verify(matchServices, times(1)).updateMatch(Mockito.any(Match.class));
		verifyNoMoreInteractions(matchServices);
	}

	@Test
	public void testDeleteMovieById() throws Exception {
		when(matchServices.deleteMatchById("1234","12")).thenReturn(true);
		mock.perform(delete("/api/v1/match/{userId}/{id}", "1234","12")).andExpect(status().isOk());
		verify(matchServices, times(1)).deleteMatchById("12","1234");
		verifyNoMoreInteractions(matchServices);
	}

	@Test
	public void testGetMovieById() throws Exception {
		when(matchServices.getMatchById(1)).thenReturn(matches.get(0));
		mock.perform(get("/api/v1/match/{id}", 1)).andExpect(status().isOk());
		verify(matchServices, times(1)).getMatchById(1);
		verifyNoMoreInteractions(matchServices);
	}

	@Test
	public void testGetMyMovies() throws Exception {
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMjM0IiwiaWF0IjoxNTUzMzM3MzU2fQ.zK0LSbjbcj2TtB742mkYCu94BqSbsaiwfXXHEB4VBbY";
		when(matchServices.getMyMatches("1234")).thenReturn(null);
		mock.perform(
				get("/api/v1/match").contentType(MediaType.APPLICATION_JSON).header("authorization", "Bearer " + token))
				.andExpect(status().isOk());
		verify(matchServices, times(1)).getMyMatches("1234");
		verifyNoMoreInteractions(matchServices);
	}

	private String jsonToString(final Object object) {
		String result;
		try {
			final ObjectMapper mapper = new ObjectMapper();
			result = mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			result = "Json processing error";
		}
		return result;
	}

}
