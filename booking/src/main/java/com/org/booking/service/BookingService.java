package com.org.booking.service;

import com.org.booking.model.Booking;
import com.org.booking.model.BookingModel;
import com.org.booking.repository.BookingModelRepository;
import com.org.booking.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class BookingService {

    @Autowired
    BookingRepository repository;

    @Autowired
    BookingModelRepository modelRepository;

    public long bookMovie(BookingModel model){
        model = dotransaction(model);
        Booking booking = new Booking(model.getBookingId(), model.getUserId());
        repository.save(booking);
        return model.getBookingId();
    }

    private BookingModel dotransaction(BookingModel model) {
        model.setBookingId(ThreadLocalRandom.current().nextLong(10000000000L));
        modelRepository.save(model);
        return model;
    }
}
