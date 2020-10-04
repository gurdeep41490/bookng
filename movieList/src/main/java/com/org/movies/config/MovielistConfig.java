package com.org.movies.config;

import com.org.movies.messaging.MovieMessageListener;
import com.org.movies.messaging.MovieMessageSender;
import com.org.movies.util.DataLoader;
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
public class MovielistConfig {

    @Value("${d11.queue.name:d11q}")
    private String queueName;

    @Value("${d11.exchange.name:d11ex}")
    private String topicExchange;

    @Value("${d11.routing.key:d11}")
    private String routingKey;

    @Bean(name="d11queue")
    public Queue queue(){
        return new Queue(queueName, false);
    }

    @Bean
    public TopicExchange topic(){
        return new TopicExchange(topicExchange);
    }

    @Bean
    public Binding createBinding(Queue queue, TopicExchange topicExchange){
        return BindingBuilder.bind(queue).to(topicExchange).with(routingKey);
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
    public MovieMessageListener movieMessageListener(){
        return new MovieMessageListener();
    }

    @Bean
    public MovieMessageSender messageSender(RabbitTemplate rabbitTemplate){
        return new MovieMessageSender(rabbitTemplate);
    }

    @Bean
    public DataLoader dataloader(){
        return new DataLoader();
    }
}
