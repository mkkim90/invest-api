package invest.dto;

import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
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

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}