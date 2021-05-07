package com.stock.spring.domain.company;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "company_info")
public class CompanyInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "company_id", nullable = false)
    private String companyId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String market;

    @Builder
    public CompanyInfo(String companyId, String name, String market) {
        this.companyId = companyId;
        this.name = name;
        this.market = market;
    }
}
