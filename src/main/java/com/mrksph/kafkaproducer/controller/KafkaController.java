package com.mrksph.kafkaproducer.controller;

import com.mrksph.kafkaproducer.producer.MyProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api")
@RequiredArgsConstructor
public class KafkaController {

    private final MyProducer producer;

    @GetMapping(path = "/send-hello-message", produces = "application/json")
    public ResponseEntity<String> sendMessage() {
        producer.produceMessage("Hello");
        return ResponseEntity.ok("Hello sent!");
    }
}
