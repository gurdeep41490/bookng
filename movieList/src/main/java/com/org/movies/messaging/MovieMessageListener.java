package com.org.movies.messaging;

import com.org.movies.model.UserFeedback;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

public class MovieMessageListener {

    @Autowired

    private static final String queueName="moviefeedback";

    @RabbitListener(queues = queueName)
    public void receiveFeedback(final UserFeedback model){
    }
}
