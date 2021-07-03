package com.stock.spring.websocket.service;

import com.stock.spring.websocket.domain.Message;
import com.stock.spring.websocket.domain.MessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatService {
    private final MessageRepository messageRepository;

    @Transactional
    public Message saveChat(Message message) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        message.setDate(LocalDateTime.now().format(formatter));
        Message entity = messageRepository.save(message);
        System.out.println(message.getContent());
        return entity;
    }

    @Transactional(readOnly = true)
    public List<Message> findAll() {
        return messageRepository.findAll();
    }
}
