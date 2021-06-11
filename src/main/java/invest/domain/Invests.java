package invest.domain;

import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Embeddable
public class Invests {
    @BatchSize(size = 1000)
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private final List<Invest> invests = new ArrayList<>();

    public void add(Invest invest) {
        this.invests.add(invest);
    }

    public BigDecimal getCurrentInvestingAmount() {
        if (invests.size() == 0) return BigDecimal.ZERO;
        return invests.stream()
                .map(Invest::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public long getNumberInvestors() {
        return invests.stream()
                .collect(Collectors.groupingBy(Invest::getUserId))
                .size();
    }

    public boolean currentInvestingAmountEquals(BigDecimal totalInvestingAmount) {
        return getCurrentInvestingAmount().equals(totalInvestingAmount);
    }
}
