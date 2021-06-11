package com.kakaopay.invest.service;

import com.kakaopay.invest.domain.Invest;
import com.kakaopay.invest.domain.Product;
import com.kakaopay.invest.dto.InvestRequest;
import com.kakaopay.invest.dto.InvestResponse;
import com.kakaopay.invest.repository.InvestRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class InvestService {
    private final InvestRepository investRepository;
    private final ProductService productService;

    public InvestService(final InvestRepository investRepository, final ProductService productService) {
        this.investRepository = investRepository;
        this.productService = productService;
    }

    @Transactional(readOnly = true)
    public List<InvestResponse> list(Long userId) {
        List<Invest> invests = investRepository.findAllByUserId(userId);
        if (invests.size() == 0) throw new IllegalArgumentException("조회되는 투자상품이 없습니다.");
        return invests.stream()
                .map(InvestResponse::of)
                .collect(Collectors.toList());
    }

    public void invest(InvestRequest investRequest) {
        Product product = productService.findById(investRequest.getProductId());
        Invest invest = new Invest(investRequest.getAmount(), investRequest.getUserId(), product);
        product.invest(invest);
        investRepository.save(invest);
        if (product.isSoldOutTotalInvestingAmount()) {
            productService.changProductStatusCompletionByInvesting(product.getId());
        }
    }
}