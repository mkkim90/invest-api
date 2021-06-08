package com.kakaopay.invest.service;

import com.kakaopay.invest.domain.Invest;
import com.kakaopay.invest.domain.Product;
import com.kakaopay.invest.dto.InvestResponse;
import com.kakaopay.invest.dto.ProductResponse;
import com.kakaopay.invest.repository.InvestRepository;
import com.kakaopay.invest.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class InvestService {
    private final InvestRepository investRepository;

    public InvestService(final InvestRepository investRepository) {
        this.investRepository = investRepository;
    }

    @Transactional(readOnly = true)
    public List<InvestResponse> list(Long userId) {
        List<Invest> invests = investRepository.findAllByUserId(userId);
        if (invests.size() == 0) throw new IllegalArgumentException("조회되는 투자상품이 없습니다.");
        return invests.stream()
                .map(InvestResponse::of)
                .collect(Collectors.toList());
    }
}