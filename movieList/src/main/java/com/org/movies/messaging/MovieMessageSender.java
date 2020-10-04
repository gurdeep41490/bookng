package com.org.movies.messaging;

import com.org.movies.model.UserFeedback;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class MovieMessageSender {

    private RabbitTemplate template;

    public MovieMessageSender(RabbitTemplate template){
        this.template = template;
    }

    public void sendMessage(UserFeedback model){
        template.convertAndSend(model);
    }
}
