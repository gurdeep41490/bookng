package com.org.jwt.service;


import com.org.jwt.model.UserFeedback;
import com.org.jwt.repository.UserFeedBackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserFeedbackService {

    @Autowired
    UserFeedBackRepository userFeedBackRepository;

    public UserFeedback save(UserFeedback feedback){
        return userFeedBackRepository.save(feedback);
    }
}
