package com.stock.spring.websocket.web.controller;

import com.stock.spring.websocket.domain.Message;
import com.stock.spring.websocket.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequiredArgsConstructor
@RestController
public class ChatController {

    private final ChatService chatService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/send-message")
    public Message boardCast(Message message) throws Exception {
        Thread.sleep(500); // delay 0.5 second!
        chatService.saveChat(message);
        simpMessagingTemplate.convertAndSend("/topic/roomId", message);

        return message;
    }

    @GetMapping("/api/v2/web-socket/topic/roomId/all")
    public List<Message> allMessage() {
        return chatService.findAll();
    }


//    @OnMessage
//    public void getMsg(Session recieveSession, String msg) {
//        for (int i = 0; i < session.size(); i++) {
//            if (!recieveSession.getId().equals(session.get(i).getId())) {
//                try{
//                    session.get(i).getBasicRemote().sendText("상대 : ", msg);
//                } catch (IOException e){
//                    e.printStackTrace();
//                }
//            } else {
//                try {
//                    session.get(i).getBasicRemote().sendText("나 : "+msg);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
}
