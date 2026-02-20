package com.info.producer.service;

import com.info.producer.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {
    @Autowired
    private KafkaTemplate<String, Student> kafkaTemplate;
    public String sendMessage(Student stud){
        kafkaTemplate.send("student-topic", "student1", stud);
        return "Student data sent to kafka successfully";
    }
}
