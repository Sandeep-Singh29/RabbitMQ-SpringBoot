package com.rabbitmq.learn.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {

    // Spring bean for RabbitMq Queue

    @Value("${spring.rabbitmq.queue.name1}")
    private String queueName;

    @Value("${spring.rabbitmq.queue.json1}")
    private String jsonQueueName;

    @Value("${spring.rabbitmq.queue_exchange1}")
    private String queue_exchange;

    @Value("${spring.rabbitmq.routing.key1}")
    private String routingQueue1;

    @Value("${spring.rabbitmq.routing.json.key1}")
    private String jsonRoutingQueue1;

    // Create a Queue Similar as Kafka in Topic
    @Bean
    public Queue queue() {
        return new Queue(queueName);
    }

    // Spring Bean for Queue to JSON-QUEUE
    @Bean
    public Queue jsonQueue() {
        return new Queue(jsonQueueName);
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

    //Bind Between Queue and Exchange Using Routing Queue >>> JSON Binding
    @Bean
    public Binding josnBinding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(jsonQueue()).to(exchange()).with(jsonRoutingQueue1);

    }


    // JSON Message Converter
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    // Configure RabbitMQ Template to Convert a Json Data
    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }

    //ConnectionFactory
    //RabbitAdmin
    //RabbitTemplate
}
