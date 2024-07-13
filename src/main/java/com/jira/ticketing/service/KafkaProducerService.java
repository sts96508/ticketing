package com.jira.ticketing.service;

import com.jira.ticketing.entity.dto.TestKafkaMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {
    private final KafkaTemplate<String, TestKafkaMsg> kafkaTemplate;

    @Autowired
    public KafkaProducerService(KafkaTemplate<String, TestKafkaMsg> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(TestKafkaMsg message) {
        kafkaTemplate.send("topic1", message);

    }
}
