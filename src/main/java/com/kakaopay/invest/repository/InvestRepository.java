package com.kakaopay.invest.repository;

import com.kakaopay.invest.domain.Invest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvestRepository extends JpaRepository<Invest, Long> {
}
