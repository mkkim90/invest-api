package invest.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

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

    public Long getId() { return id;}

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

    public Long getUserId() {
        return userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invest invest = (Invest) o;
        return Objects.equals(id, invest.id) && Objects.equals(amount, invest.amount) && Objects.equals(userId, invest.userId) && Objects.equals(product, invest.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, userId, product);
    }
}
