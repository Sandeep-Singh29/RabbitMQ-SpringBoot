package com.rabbitmq.learn.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitMQConsumer {

    @RabbitListener(queues = "${spring.rabbitmq.queue.name1}")
    public void consumeMessage(String message) {
        log.info("Message Received By RabbitMQConsumer : {}", message);
    }

}
