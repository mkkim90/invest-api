package com.kakaopay.invest.api;

import com.kakaopay.invest.dto.InvestResponse;
import com.kakaopay.invest.dto.ProductResponse;
import com.kakaopay.invest.service.InvestService;
import com.kakaopay.invest.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InvestApi {
    private final InvestService investService;

    public InvestApi(final InvestService investService) {
        this.investService = investService;
    }
/*
    @PostMapping("/api/product")
    public ResponseEntity<OrderResponse> create(@RequestBody final OrderRequest order) {
        final OrderResponse created = orderService.create(order);
        final URI uri = URI.create("/api/orders/" + created.getId());
        return ResponseEntity.created(uri)
                .body(created);
    }
*/
    @GetMapping("/api/invest")
    public ResponseEntity<List<InvestResponse>> listByUserId(@RequestHeader(name = "X-USER-ID") Long userId) {
        return ResponseEntity.ok()
                .body(investService.list(userId));
    }
}
