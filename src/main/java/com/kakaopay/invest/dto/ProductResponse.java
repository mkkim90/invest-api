package com.kakaopay.invest.dto;

import com.kakaopay.invest.domain.Product;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProductResponse {
    private Long id;
    private String title;
    private BigDecimal totalInvestingAmount;
    private BigDecimal currentInvestingAmount;
    private long numberInvestors;
    private String productStatus;
    private LocalDateTime startedAt;
    private LocalDateTime finishedAt;

    public ProductResponse() {
    }

    public ProductResponse(Long id, String title, BigDecimal totalInvestingAmount, BigDecimal currentInvestingAmount, long numberInvestors, String productStatus, LocalDateTime startedAt, LocalDateTime finishedAt) {
        this.id = id;
        this.title = title;
        this.totalInvestingAmount = totalInvestingAmount;
        this.currentInvestingAmount = currentInvestingAmount;
        this.numberInvestors = numberInvestors;
        this.productStatus = productStatus;
        this.startedAt = startedAt;
        this.finishedAt = finishedAt;
    }

    public static ProductResponse of(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getTitle(),
                product.getTotalInvestingAmount(),
                product.getCurrentInvestingAmount(),
                product.getNumberInvestors(),
                product.getProductStatus().name(),
                product.getStartedAt(),
                product.getFinishedAt()
        );
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

    public BigDecimal getCurrentInvestingAmount() {
        return currentInvestingAmount;
    }

    public long getNumberInvestors() {
        return numberInvestors;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public LocalDateTime getFinishedAt() {
        return finishedAt;
    }
}