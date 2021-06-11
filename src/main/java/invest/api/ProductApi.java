package invest.api;

import invest.dto.ProductResponse;
import invest.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductApi {
    private final ProductService productService;

    public ProductApi(final ProductService productService) {
        this.productService = productService;
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
    @GetMapping("/api/products")
    public ResponseEntity<List<ProductResponse>> list() {
        return ResponseEntity.ok()
                .body(productService.list());
    }
}
