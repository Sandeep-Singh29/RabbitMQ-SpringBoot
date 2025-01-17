package com.rabbitmq.learn.consumer;

import com.rabbitmq.learn.dto.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RabbitMQJsonConsumer {

    private RabbitMQConsumer rabbitMQConsumer;

    @RabbitListener(queues = "${spring.rabbitmq.queue.json1}")
    public void consumeJsonMessage(User user) {
        log.info("User JSON Data Consumed By RabbitMQJsonConsumer  : {}", user);
    }


}
