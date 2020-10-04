package com.org.booking.controller;

import com.org.booking.model.BookingModel;
import com.org.booking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class BookingController {

    @Autowired
    BookingService bookingService;

     @PostMapping(path="bookMovie")
    public Long bookMovie(@RequestParam("BookingModel") BookingModel model){
        return bookingService.bookMovie(model);
     }
}
