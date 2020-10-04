package com.org.movies.service;

import com.org.movies.model.Hall;
import com.org.movies.model.Language;
import com.org.movies.model.Movie;
import com.org.movies.model.Theatre;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MovieServiceDAOTest {

    @Autowired
    private TestEntityManager entityManager;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private Movie movie;

    @Before
    public void setUp(){
        movie = fillOutMovieDetails();
    }

    @Test
    public void checkMovie(){
        Movie movie1 = this.entityManager.persistAndFlush(movie);
        assertThat(movie1.getLanguage(), is(equalTo(Language.HINDI)));
        assertThat(movie1.getTheatres().size(), is(equalTo(2)));
    }

    @Test
    public void checkTheatres(){
        Theatre theatre = this.entityManager.persistAndFlush(getTheatre());
        assertThat(theatre.getTheatreId(), is(equalTo(1)));
    }

    private Movie fillOutMovieDetails() {
        Movie movie = new Movie( Language.HINDI, "abc", true, 75,
                LocalDate.of(2020, 04, 14), LocalDate.of(2020, 04, 28));
        Set<Theatre> theatres = getTheatres();
        for(Theatre theatre: theatres){
            movie.addTheatre(theatre);
        }
        return movie;
    }

    private Theatre getTheatre(){
        return new Theatre("def", "ghi", getHalls(3));
    }

    private Set<Theatre> getTheatres() {
        Theatre theatre = new Theatre("def", "ghi", getHalls(3));
        Theatre theatre2 = new Theatre("abc", "jkl", getHalls(2));

        Set<Theatre> theatres = new HashSet<>();
        theatres.add(theatre);
        theatres.add(theatre2);

        return theatres;
    }

    private Set<Hall> getHalls(int number){
        Set<Hall> halls = new HashSet<>();
        for(int i  = 0; i < number; i++){
            Hall hall = new Hall(i, 10, 10 ,10);
            halls.add(hall);
        }
        return halls;
    }
}
