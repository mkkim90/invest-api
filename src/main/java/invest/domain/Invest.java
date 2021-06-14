package invest.domain;

import lombok.Getter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
public class Invest extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(name="user_id", nullable = false)
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    protected Invest() {
    }

    public Invest(BigDecimal amount, Long userId, Product product) {
        validateRequred(amount, userId, product);
        this.amount = amount;
        this.userId = userId;
        this.product = product;
    }

    private void validateRequred(BigDecimal amount, Long userId, Product product) {
        if (amount == null || userId == null || product == null) {
            throw new IllegalArgumentException("투자 필수값 누락입니다.");
        }
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
