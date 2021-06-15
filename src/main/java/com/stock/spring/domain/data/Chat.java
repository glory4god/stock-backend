package com.stock.spring.domain.data;

import com.stock.spring.domain.BaseTimeCreateTImeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "chatting_record")
public class Chat extends BaseTimeCreateTImeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "content", nullable = false)
    private String content;

    @Builder
    public Chat(String userName, String content) {
        this.userName = userName;
        this.content = content;
    }
}
