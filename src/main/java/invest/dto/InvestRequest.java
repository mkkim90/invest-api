package invest.dto;

import java.math.BigDecimal;

public class InvestRequest {
    private Long productId;
    private BigDecimal amount;
    private Long userId;

    public InvestRequest() {
    }

    public InvestRequest(Long productId, BigDecimal amount, Long userId) {
        this.productId = productId;
        this.amount = amount;
        this.userId = userId;
    }

    public InvestRequest(Long productId, BigDecimal amount) {
        this.productId = productId;
        this.amount = amount;
    }

    public Long getProductId() {
        return productId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return this.userId;
    }
}