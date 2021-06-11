package invest.service;

import invest.domain.Product;
import invest.dto.ProductResponse;
import invest.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional(readOnly = true)
    public List<ProductResponse> list() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        List<Product> products = productRepository.findAllByStartedAtBeforeAndFinishedAtAfter(currentDateTime, currentDateTime);
        return products.stream()
                .map(ProductResponse::of)
                .collect(Collectors.toList());
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void changProductStatusCompletionByInvesting(Long id) {
        Product product = findById(id);
        product.changeCompleteStatus();
    }

    @Transactional(readOnly = true)
    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }
}