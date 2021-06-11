package invest.api;

import invest.dto.InvestRequest;
import invest.dto.InvestResponse;
import invest.service.InvestService;
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

    @PostMapping("/api/invest")
    public ResponseEntity<InvestResponse> invest(@RequestHeader(name = "X-USER-ID") final Long userId, @RequestBody final InvestRequest investRequest) {
        investRequest.setUserId(userId);
        InvestResponse investResponse = investService.invest(investRequest);
        return ResponseEntity.created(URI.create("/api/invest/" + investResponse.getId())).body(investResponse);
    }

    @GetMapping("/api/invest/{id}")
    public ResponseEntity<InvestResponse> showInvest(@PathVariable Long id) {
        return ResponseEntity.ok().body(investService.findById(id));
    }

    @GetMapping("/api/invest")
    public ResponseEntity<List<InvestResponse>> listByUserId(@RequestHeader(name = "X-USER-ID") final Long userId) {
        return ResponseEntity.ok()
                .body(investService.list(userId));
    }
}
