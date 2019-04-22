package com.stackroute.favouriteservice.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.favouriteservice.domain.Match;




@Transactional
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class MatchRepoTest {

                
                @Autowired
                private transient MatchRepository repo;

                
                public void setrepo(MatchRepository repo) 
                {
                                this.repo = repo;
                }

                @After
                public void tearDown(){
                	repo.deleteAllInBatch();
                }
                @Test
                public void testSaveMovie() throws Exception
                {
                               Match match1= repo.save(new Match(1, "12", "1234", "duo", "india", "erangal", "new"));
                                Match match = repo.getOne(match1.getFavId());
                                assertEquals(match.getMapName(),"erangal");
                                
                                
                }
                
                @Test
                public void testUpdateMovie() throws Exception
                {
                	Match match1=new Match(1, "12", "1234", "duo", "india", "erangal", "new");
                                Match match2=repo.save(match1);
                                final Match match = repo.getOne(match2.getFavId());
                                assertEquals(match.getGameMode(),"duo");
                                match.setGameMode("squad");
                                Match match3=repo.save(match);
                                final Match tempMovie = repo.getOne(match3.getFavId());
                                assertEquals("squad",tempMovie.getGameMode());
                
                }              
                
                @Test
                public void testDeleteMovie() throws Exception
                {
                                Match match1=repo.save(new Match(1, "12", "1234", "duo", "india", "erangal", "new"));
                                final Match match = repo.getOne(match1.getFavId());
                                assertEquals(match.getMapName(),"erangal");
                                repo.delete(match);
                                assertEquals(Optional.empty(),repo.findById(match.getFavId()));
                                
                }
                
                @Test
                public void testGetMovie() throws Exception
                {
                                Match match1=repo.save(new Match(1, "12", "1234", "duo", "india", "erangal", "new"));
                                final Match match = repo.getOne(match1.getFavId());
                                assertEquals(match.getMapName(),"erangal");
                }
                
                @Test
                public void testGetMyMovies() throws Exception
                {
                                repo.save(new Match(1, "12", "134", "duo", "india", "erangal", "new"));
                                repo.save(new Match(2, "13", "124", "duo", "india", "erangal", "new"));
                                repo.save(new Match(3, "14", "123", "duo", "india", "erangal", "new"));
                                repo.save(new Match(4, "15", "234", "duo", "india", "erangal", "new"));
                                final List<Match> matches = repo.findAll();
                                assertEquals(matches.get(0).getMapName(),"erangal");
                }
                
                
                
}

