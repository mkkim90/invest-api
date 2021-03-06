package invest.repository;

import invest.domain.Product;
import invest.domain.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByStartedAtBeforeAndFinishedAtAfter(LocalDateTime startedAt, LocalDateTime finishedAt);

    Product findByProductStatus(ProductStatus completion);
}
