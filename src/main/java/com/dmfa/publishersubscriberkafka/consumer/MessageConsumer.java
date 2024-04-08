package com.dmfa.publishersubscriberkafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    @KafkaListener(topics = "user_created", groupId = "user_consumer")
    public void listen(String message) {
        try {
            Thread.sleep(3000);
        } catch (Exception e){
            System.err.println(e.getMessage());
        }

        System.out.println("<<<<< Received message: " + message);
    }
}
