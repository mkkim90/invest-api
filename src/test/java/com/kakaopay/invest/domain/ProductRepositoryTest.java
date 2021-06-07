package com.kakaopay.invest.domain;

import com.kakaopay.invest.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @DisplayName("상품 등록")
    @Test
    void create() {
        LocalDateTime startAt = LocalDateTime.now();
        LocalDateTime finishAt = startAt.plusMonths(1);
        Product expected = new Product("투자상품", BigDecimal.valueOf(1000), startAt, finishAt);
        Product product = productRepository.save(expected);
        assertThat(expected == product).isTrue();
    }

    @DisplayName("상품 등록 예외 - 가격이 0미만")
    @Test
    void validLessThanZero() {
        Assertions.assertThatThrownBy(() -> {
            new Product("투자상품", BigDecimal.valueOf(-1), LocalDateTime.now(), LocalDateTime.now().plusMonths(1));
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
