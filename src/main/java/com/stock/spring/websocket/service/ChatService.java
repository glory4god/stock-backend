package com.stock.spring.websocket.service;

import com.stock.spring.websocket.domain.Message;
import com.stock.spring.websocket.domain.MessageRepository;
import com.stock.spring.websocket.web.MessageRequestDto;
import com.stock.spring.websocket.web.MessageResponseDto;
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
    public MessageResponseDto saveChat(MessageRequestDto message) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Message message1 = new Message(message.getUsername(), message.getContent(),LocalDateTime.now().format(formatter));
        messageRepository.save(message1);
        System.out.println(message1.getUsername() +":"+ message1.getContent());
        return new MessageResponseDto(message1);
    }

    @Transactional(readOnly = true)
    public List<Message> findAll() {
        return messageRepository.findAll();
    }
}
