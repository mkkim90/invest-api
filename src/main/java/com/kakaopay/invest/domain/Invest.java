package com.kakaopay.invest.domain;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Invest extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(name="user_id")
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    protected Invest() {
    }

    public Invest(BigDecimal amount, Long userId, Product product) {
        this.amount = amount;
        this.userId = userId;
        this.product = product;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Long getProductId() {
        return product.getId();
    }

    public String getProductTitle() {
        return product.getTitle();
    }

    public BigDecimal getTotalInvestingAmount() {
        return product.getTotalInvestingAmount();
    }


}
