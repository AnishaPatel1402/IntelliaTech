package com.info.producer.controller;

import com.info.producer.entity.Student;
import com.info.producer.service.KafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/producer")
public class StudentController {
    @Autowired
    private KafkaService kafkaService;
    @PostMapping
    public ResponseEntity<String> addStudent(@RequestBody Student stud){
        String msg =  kafkaService.sendMessage(stud);
        return new ResponseEntity<String>(msg, HttpStatus.CREATED);
    }


}
