package com.example.springavrokakademo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class KafkaPublisher {
    
    private final KafkaTemplate<String, Order> kafkaTemplate;
    private final String topic;
    
    public KafkaPublisher(KafkaTemplate<String, Order> kafkaTemplate,
                          @Value("${kafka.topics.order}") String orderTopic) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = orderTopic;
    }
    
    public CompletableFuture<SendResult<String, Order>> publish(Order order) {
        
        return kafkaTemplate.send(topic, order).completable();
    }
}
