package com.kakaopay.invest.domain;

import kitchenpos.order.dto.OrderLineItemResponse;
import org.springframework.util.CollectionUtils;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Embeddable
public class Invests {
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "product_id")
    private final List<Invest> invests = new ArrayList<>();

    public void add(Long productId, List<Invest> Invests) {
        if (CollectionUtils.isEmpty(invests)) {
            throw new IllegalArgumentException();
        }

        for (final Invest invest : invests) {
            this.invests.add(invests);
        }
    }

    public List<Invest> getInvests() {
        return Collections.unmodifiableList(invests);
    }

    public BigDecimal getCurrentInvestingAmount() {
        return invests.stream()
                .map(OrderLineItemResponse::of)
                .collect(Collectors.toList());
    }
}
