package com.kakaopay.invest.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Product extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private BigDecimal totalInvestingAmount;

    private LocalDateTime startedAt;

    private LocalDateTime finishedAt;

    protected Product() {
    }

    public Product(String title, BigDecimal totalInvestingAmount, LocalDateTime startedAt, LocalDateTime finishedAt) {
        validateLessThanZero(totalInvestingAmount);
        this.title = title;
        this.totalInvestingAmount = totalInvestingAmount;
        this.startedAt = startedAt;
        this.finishedAt = finishedAt;
    }

    private void validateLessThanZero(BigDecimal totalInvestingAmount) {
        if (BigDecimal.ZERO.compareTo(totalInvestingAmount) > 0) {
            throw new IllegalArgumentException("가격이 0보다 적을 수 없습니다.");
        }
    }

}
