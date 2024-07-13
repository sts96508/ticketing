package com.jira.ticketing.service;

import com.jira.ticketing.entity.dto.TestKafkaMsg;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "topic1", groupId = "grp2")
    public void consume(TestKafkaMsg message) {
        System.out.println(message.getId() + " " + message.getMsg());
    }
}
