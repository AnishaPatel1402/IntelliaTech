package com.info.consumer.service;

import com.info.consumer.entity.Student;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {
    private String message;
    @KafkaListener(topics = "student-topic", groupId = "consumer-group1")
    public void consumeData(Student stud){
        message =   stud + " got the data from kafka";
        System.out.println(message);
    }

    public String getMessage() {
        return message;
    }
}
