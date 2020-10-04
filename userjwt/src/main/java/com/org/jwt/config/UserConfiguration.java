package com.org.jwt.config;


import com.org.jwt.messaging.FeedbackListener;
import com.org.jwt.messaging.FeedbackSender;
import com.org.jwt.service.UserFeedbackService;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfiguration {

    @Value("${moviebooking.queue.name}")
    private String queueName;

    @Value("${moviebooking.exchange.name}")
    private String exchangeName;

    @Value("${moviebooking.routing.key}")
    private String routingKey;

    @Bean
    public Queue queue(){ return new Queue(queueName, false);}

    @Bean
    public TopicExchange exchange(){ return new TopicExchange(exchangeName);}

    @Bean
    public Binding binding(){
        return BindingBuilder.bind(queue()).to(exchange()).with(routingKey);
    }

    @Bean
    public FeedbackListener messageListener(UserFeedbackService service){
        return new FeedbackListener();
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter(){return new Jackson2JsonMessageConverter();}

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory factory){
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(factory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }

    @Bean
    public FeedbackSender feedbackSender(RabbitTemplate template){
        return new FeedbackSender(template);
    }
}
