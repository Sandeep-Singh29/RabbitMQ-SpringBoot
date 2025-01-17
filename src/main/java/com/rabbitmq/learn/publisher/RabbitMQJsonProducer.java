package com.rabbitmq.learn.publisher;

import com.rabbitmq.learn.dto.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class RabbitMQJsonProducer {

    @Value("${spring.rabbitmq.queue_exchange1}")
    private String exchange;

    @Value("${spring.rabbitmq.routing.json.key1}")
    private String jsonRoutingKey;

    private final RabbitTemplate rabbitTemplate;


    public void sendJsonMessage(User user) {
        user.setId(UUID.randomUUID().toString());
        log.info("User JSON Data Send By RabbitMQProducer : {}", user);
        rabbitTemplate.convertAndSend(exchange, jsonRoutingKey, user);
    }

}
