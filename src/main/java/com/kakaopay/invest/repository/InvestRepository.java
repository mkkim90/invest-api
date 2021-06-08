package com.kakaopay.invest.repository;

import com.kakaopay.invest.domain.Invest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvestRepository extends JpaRepository<Invest, Long> {
    List<Invest> findAllByUserId(Long userId);
}
