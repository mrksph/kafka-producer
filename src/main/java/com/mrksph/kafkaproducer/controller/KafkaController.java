package com.mrksph.kafkaproducer.controller;

import com.mrksph.kafkaproducer.config.KafkaAdminConfig;
import com.mrksph.kafkaproducer.producer.KafkaProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api")
@RequiredArgsConstructor
@Slf4j
public class KafkaController {

    private final KafkaProducer producer;
    private final KafkaAdminConfig admin;

    @GetMapping(path = "/send-hello-message", produces = "application/json")
    public ResponseEntity<String> sendMessage() {
        producer.produceMessage("Hello");
        return ResponseEntity.ok("Hello sent!");
    }

    @GetMapping(path = "/create-topic", produces = "application/json")
    public ResponseEntity<String> createTopic() {
        admin.newTopic();
        log.debug("New Topic created!");
        return ResponseEntity.ok("Topic created sent!");
    }
}
