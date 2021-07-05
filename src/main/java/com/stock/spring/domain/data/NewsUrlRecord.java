package com.stock.spring.domain.data;

import com.stock.spring.domain.BaseTimeCreateTImeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "news_url_record")
public class NewsUrlRecord extends BaseTimeCreateTImeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "link", nullable = false)
    private String link;

    @Column(name = "originallink", nullable = false)
    private String originallink;

    @Column(name = "pubDate", nullable = false)
    private String pubDate;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "keyword", nullable = false)
    private String keyword;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Builder
    public NewsUrlRecord(String title, String link, String originallink, String pubDate, String description, String keyword, String imageUrl
    ) {

        this.title = title;
        this.link = link;
        this.originallink = originallink;
        this.description = description;
        this.pubDate = pubDate;
        this.keyword = keyword;
        this.imageUrl = imageUrl;
    }

}
