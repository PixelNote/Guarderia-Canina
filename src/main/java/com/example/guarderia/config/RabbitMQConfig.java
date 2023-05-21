package com.example.guarderia.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;

@Configuration
public class RabbitMQConfig {

    private static final String EXCHANGE_NAME = "notificationExchange";
    private static final String QUEUE_NAME = "notificationQueue";
    private static final String ROUTING_KEY = "message";

    @Bean
    public Exchange exchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(exchange()).with(ROUTING_KEY).noargs();
    }

    @Bean
    public AmqpAdmin amqpAdmin(ConnectionFactory connectionFactory) {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        rabbitAdmin.declareExchange(exchange());
        rabbitAdmin.declareQueue(queue());
        rabbitAdmin.declareBinding(binding());
        return rabbitAdmin;
    }
}