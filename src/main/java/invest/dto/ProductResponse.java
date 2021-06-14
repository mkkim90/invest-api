package invest.dto;

import invest.domain.Product;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
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

    @Builder
    private ProductResponse(Long id, String title, BigDecimal totalInvestingAmount, BigDecimal currentInvestingAmount, long numberInvestors, String productStatus, LocalDateTime startedAt, LocalDateTime finishedAt) {
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
        return ProductResponse.builder()
                .id(product.getId())
                .title(product.getTitle())
                .totalInvestingAmount(product.getTotalInvestingAmount())
                .currentInvestingAmount(product.getCurrentInvestingAmount())
                .numberInvestors(product.getNumberInvestors())
                .productStatus(product.getProductStatus().name())
                .startedAt(product.getStartedAt())
                .finishedAt(product.getFinishedAt())
                .build();
    }
}