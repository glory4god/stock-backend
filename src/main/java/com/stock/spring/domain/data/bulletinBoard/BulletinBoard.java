package com.stock.spring.domain.data.bulletinBoard;

import com.stock.spring.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Getter
@Table(name = "bulletin_board")
public class BulletinBoard extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userId", nullable = false)
    private Long userId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "good", nullable = false)
    private int good;

    @Column(name = "views", nullable = false)
    private int views;

    @Builder
    public BulletinBoard(Long userId, String title, String content, int good, int views) {
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.good = good;
        this.views = views;
    }

}

