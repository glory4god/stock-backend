package com.stock.spring.kafkachat.consumer;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@RequiredArgsConstructor
@Service
public class ConsumerService {

    @KafkaListener(topics = "oingdaddy", groupId = "group-id-oing")
    public void consume(String message) throws IOException {
        System.out.println("receive message : " + message);
    }
}
