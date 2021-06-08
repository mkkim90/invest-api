package com.kakaopay.invest.service;

import com.kakaopay.invest.domain.Product;
import com.kakaopay.invest.dto.ProductResponse;
import com.kakaopay.invest.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional(readOnly = true)
    public List<ProductResponse> list() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        List<Product> products = productRepository.findAllByStartedAtBeforeAndFinishedAtAfter(currentDateTime, currentDateTime);
        return products.stream()
                .map(ProductResponse::of)
                .collect(Collectors.toList());
    }
}
