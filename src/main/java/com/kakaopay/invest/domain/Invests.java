package com.kakaopay.invest.domain;

import org.hibernate.annotations.BatchSize;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Embeddable
public class Invests {
    @BatchSize(size = 1000)
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "product_id")
    private final List<Invest> invests = new ArrayList<>();

    public void add(Long productId, List<Invest> Invests) {
        if (CollectionUtils.isEmpty(invests)) {
            throw new IllegalArgumentException();
        }
        for (final Invest invest : invests) {
            this.invests.add(invest);
        }
    }

    public List<Invest> getInvests() {
        return Collections.unmodifiableList(invests);
    }

    public BigDecimal getCurrentInvestingAmount() {
        if (invests.size() == 0) return BigDecimal.ZERO;
        return invests.stream()
                .map(Invest::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public long getNumberInvestors() {
        return invests.size();
    }
}
