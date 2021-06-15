package com.stock.spring.domain.data;

import com.stock.spring.domain.BaseTimeCreateTImeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "news_keyword_record")
public class NewsKeywordRecord extends BaseTimeCreateTImeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "keyword", nullable = false)
    private String keyword;

    @Builder
    public NewsKeywordRecord(String keyword) {
        this.keyword = keyword;
    }
}
