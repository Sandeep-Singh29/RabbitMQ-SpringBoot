package com.rabbitmq.learn.controller;

import com.rabbitmq.learn.dto.User;
import com.rabbitmq.learn.publisher.RabbitMQJsonProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UserJSONController {

    private final RabbitMQJsonProducer rabbitMQJsonProducer;

    public UserJSONController(RabbitMQJsonProducer rabbitMQJsonProducer) {
        this.rabbitMQJsonProducer = rabbitMQJsonProducer;
    }

    @PostMapping("/user-publish")
    public ResponseEntity<String> userJsonMessage(@RequestBody User user) {
        rabbitMQJsonProducer.sendJsonMessage(user);
        return ResponseEntity.ok("User Successfully Send..!!!");
    }

}
