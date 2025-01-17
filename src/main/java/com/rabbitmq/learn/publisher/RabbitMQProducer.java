package com.rabbitmq.learn.publisher;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitMQProducer {

    @Value("${spring.rabbitmq.queue_exchange1}")
    private String exchange;

    @Value("${spring.rabbitmq.routing.key1}")
    private String routingKey;

    private final RabbitTemplate rabbitTemplate;

    public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    public void sendMessage(String message) {
        log.info("Message Send By RabbitMQProducer : {}", message);
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }

}
