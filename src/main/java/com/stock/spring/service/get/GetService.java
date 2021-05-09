package com.stock.spring.service.get;

import com.stock.spring.domain.company.CompanyRepository;
import com.stock.spring.web.dto.GetCompanyResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class GetService {
    private final CompanyRepository companyRepository;

    @Transactional(readOnly = true)
    public List<GetCompanyResponseDto> findByMarket(String market) {
        return companyRepository.findByMarket(market).stream()
                .map(GetCompanyResponseDto::new)
                .collect(Collectors.toList());

    }


}
