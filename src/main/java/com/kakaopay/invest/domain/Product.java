package com.kakaopay.invest.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.nio.file.FileStore;
import java.time.LocalDateTime;
import java.util.List;

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

    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus = ProductStatus.INVESTING;

    @Embedded
    private final Invests invests = new Invests();


    protected Product() {
    }

    public Product(String title, BigDecimal totalInvestingAmount, LocalDateTime startedAt, LocalDateTime finishedAt) {
        validateLessThanZero(totalInvestingAmount);
        validateStartedAtAndFinishedAt(startedAt, finishedAt);
        this.title = title;
        this.totalInvestingAmount = totalInvestingAmount;
        this.startedAt = startedAt;
        this.finishedAt = finishedAt;
    }

    private void validateStartedAtAndFinishedAt(LocalDateTime startedAt, LocalDateTime finishedAt) {
        if (startedAt.isAfter(finishedAt)) {
            throw new IllegalArgumentException("모집 시작 시간과 모집 종료 시간이 올바르지 않습니다.");
        }
    }

    private void validateLessThanZero(BigDecimal totalInvestingAmount) {
        if (BigDecimal.ZERO.compareTo(totalInvestingAmount) > 0) {
            throw new IllegalArgumentException("가격이 0보다 적을 수 없습니다.");
        }
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public BigDecimal getTotalInvestingAmount() {
        return totalInvestingAmount;
    }

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public LocalDateTime getFinishedAt() {
        return finishedAt;
    }

    public long getNumberInvestors() {
        return invests.getNumberInvestors();
    }

    public BigDecimal getCurrentInvestingAmount() {
        return invests.getCurrentInvestingAmount();
    }

    public ProductStatus getProductStatus() {
        return productStatus;
    }
}
