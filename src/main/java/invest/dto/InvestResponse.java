package invest.dto;

import invest.domain.Invest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class InvestResponse {
    private Long id;
    private Long productId;
    private String productTitle;
    private BigDecimal totalInvestingAmount;
    private BigDecimal myInvestingAmount;
    private LocalDateTime investDate;

    public InvestResponse() {
    }

    public InvestResponse(Long id, Long productId, String productTitle, BigDecimal totalInvestingAmount, BigDecimal myInvestingAmount, LocalDateTime investDate) {
        this.id = id;
        this.productId = productId;
        this.productTitle = productTitle;
        this.totalInvestingAmount = totalInvestingAmount;
        this.myInvestingAmount = myInvestingAmount;
        this.investDate = investDate;
    }

    public static InvestResponse of(Invest invest) {
        return new InvestResponse(
               invest.getId(),
               invest.getProductId(),
               invest.getProductTitle(),
               invest.getTotalInvestingAmount(),
               invest.getAmount(),
               invest.getCreatedDate()
        );
    }

    public Long getId() {
        return id;
    }

    public Long getProductId() {
        return productId;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public BigDecimal getTotalInvestingAmount() {
        return totalInvestingAmount;
    }

    public BigDecimal getMyInvestingAmount() {
        return myInvestingAmount;
    }

    public LocalDateTime getInvestDate() {
        return investDate;
    }
}
