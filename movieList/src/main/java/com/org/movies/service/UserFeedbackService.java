package com.org.movies.service;

import com.org.movies.model.UserFeedback;
import com.org.movies.repositories.UserFeedBackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserFeedbackService {

    @Autowired
    UserFeedBackRepository feedBackRepository;

    public UserFeedback save(UserFeedback feedback){
       return feedBackRepository.save(feedback);
    }
}
