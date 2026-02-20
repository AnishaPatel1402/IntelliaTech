package com.info.consumer.controller;

import com.info.consumer.service.KafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {
    @Autowired
    private KafkaService kafkaService;

    @GetMapping
    public ResponseEntity<String> getStudent(){
        String response = kafkaService.getMessage();
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}
