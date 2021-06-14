package invest.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import invest.domain.Invest;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public class InvestResponse {
    private Long id;
    private Long productId;
    private String productTitle;
    private BigDecimal totalInvestingAmount;
    private BigDecimal myInvestingAmount;
    private LocalDateTime investDate;

    public InvestResponse() {
    }

    @Builder
    private InvestResponse(Long id, Long productId, String productTitle, BigDecimal totalInvestingAmount, BigDecimal myInvestingAmount, LocalDateTime investDate) {
        this.id = id;
        this.productId = productId;
        this.productTitle = productTitle;
        this.totalInvestingAmount = totalInvestingAmount;
        this.myInvestingAmount = myInvestingAmount;
        this.investDate = investDate;
    }

    public static InvestResponse of(Invest invest) {

        return InvestResponse.builder()
                .id(invest.getId())
                .productId(invest.getProductId())
                .productTitle(invest.getProductTitle())
                .totalInvestingAmount(invest.getTotalInvestingAmount())
                .myInvestingAmount(invest.getAmount())
                .investDate(invest.getCreatedDate())
                .build();
    }
}
