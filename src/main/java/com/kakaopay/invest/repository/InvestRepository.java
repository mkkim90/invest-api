package com.kakaopay.invest.repository;

import com.kakaopay.invest.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
