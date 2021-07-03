package com.stock.spring.websocket.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "chatting")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "content" , nullable = false)
    private String content;

    @Column(name = "date", nullable = false)
    private String date;

    @Builder
    public Message(String username, String content) {
        this.username = username;
        this.content = content;
    }
}
