package com.rabbitmq.learn.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {

    // Spring bean for RabbitMq Queue

    @Value("${spring.rabbitmq.queue.name1}")
    private String queueName;

    @Value("${spring.rabbitmq.queue_exchange1}")
    private String queue_exchange;

    @Value("${spring.rabbitmq.routing.key1}")
    private String routingQueue1;

    // Create a Queue Similar as Kafka in Topic
    @Bean
    public Queue queue() {
        return new Queue(queueName);
    }

    // rabbitMQ exchange
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(queue_exchange);
    }

    //Bind Between Queue and Exchange Using Routing Queue.
    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue()).to(exchange()).with(routingQueue1);

    }


    //ConnectionFactory
    //RabbitAdmin
    //RabbitTemplate
}
