package com.org.jwt.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class FeedbackSender {

    private RabbitTemplate template;

    public FeedbackSender(RabbitTemplate template){
        this.template = template;
    }

   /* public void sendMessage(BookingModel model){
        template.convertAndSend(model);
    }*/
}
