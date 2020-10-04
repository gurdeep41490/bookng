package com.org.jwt.messaging;


import com.org.jwt.model.UserFeedback;
import com.org.jwt.service.UserFeedbackService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

public class FeedbackListener {

    private static final String queueName="moviefeedback";

    @Autowired
    UserFeedbackService feedbackService;

    @RabbitListener(queues = queueName)
    public void receiveFeedback(final UserFeedback model){
        feedbackService.save(model);
    }
}
