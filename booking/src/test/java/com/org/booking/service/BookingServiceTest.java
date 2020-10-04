package com.org.booking.service;

import com.org.booking.model.BookingModel;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.time.LocalDate;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
public class BookingServiceTest {

    @Autowired
    private TestEntityManager entityManager;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private BookingModel model = null;

    @Before
    public void setUp(){
          model = new BookingModel("abc", 2, 3, 4, Date.valueOf(LocalDate.now()));
    }

    @Test
    public void checkBookingMade(){
        BookingModel modelRet = this.entityManager.persistAndFlush(model);
        assertThat(modelRet.getMovieId(), is(equalTo(2L)));
    }
}
