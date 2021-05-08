package com.stock.spring.domain.company;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "company_id", nullable = false)
    private String companyId;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "market",nullable = false)
    private String market;

    @Builder
    public Company(String companyId, String name, String market) {
        this.companyId = companyId;
        this.name = name;
        this.market = market;
    }
}
