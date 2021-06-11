package com.kakaopay.invest.api;

import com.kakaopay.invest.dto.InvestRequest;
import com.kakaopay.invest.dto.InvestResponse;
import com.kakaopay.invest.service.InvestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class InvestApi {
    private final InvestService investService;

    public InvestApi(final InvestService investService) {
        this.investService = investService;
    }
/*
    @PostMapping("/api/invest")
    public ResponseEntity<InvestResponse> invest(@RequestHeader(name = "X-USER-ID") final Long userId, @RequestBody final InvestRequest investRequest) {
        investRequest.setUserId(userId);
        final InvestResponse created = investService.invest(investRequest);
        final URI uri = URI.create("/api/orders/" + created.getId());
        return ResponseEntity.created(uri)
                .body(created);
    }*/

    @GetMapping("/api/invest")
    public ResponseEntity<List<InvestResponse>> listByUserId(@RequestHeader(name = "X-USER-ID") final Long userId) {
        return ResponseEntity.ok()
                .body(investService.list(userId));
    }
}
