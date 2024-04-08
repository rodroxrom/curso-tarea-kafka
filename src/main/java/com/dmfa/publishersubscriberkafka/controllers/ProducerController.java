package com.dmfa.publishersubscriberkafka.controllers;

import ch.qos.logback.core.net.ObjectWriter;
import com.dmfa.publishersubscriberkafka.beans.BasicData;
import com.dmfa.publishersubscriberkafka.config.KafkaProducerConfig;
import com.dmfa.publishersubscriberkafka.producer.MessageProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController("/")
public class ProducerController {

    private MessageProducer messageProducer;

    @Autowired
    public ProducerController(MessageProducer messageProducer){
        this.messageProducer = messageProducer;
    }
    @PostMapping(path = "users")
    public void createUser(){
        BasicData basicData = new BasicData();
        basicData.setDescription("Hola munto %s".formatted(new Date()));
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            messageProducer.sendMessage("user_created", objectMapper.writeValueAsString(basicData));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
