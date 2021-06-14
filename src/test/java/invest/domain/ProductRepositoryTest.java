package invest.domain;

import invest.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @DisplayName("상품 등록")
    @Test
    void create() {
        LocalDateTime startAt = LocalDateTime.now();
        LocalDateTime finishAt = startAt.plusMonths(1);
        Product expected = buildProduct(startAt, finishAt);
        Product product = productRepository.save(expected);
        assertThat(expected == product).isTrue();
    }

    private Product buildProduct(LocalDateTime startAt, LocalDateTime finishAt) {
        return Product.builder()
                .title("투자상품")
                .totalInvestingAmount(BigDecimal.valueOf(1_000))
                .startedAt(startAt)
                .finishedAt(finishAt)
                .build();
    }


    @DisplayName("상품 등록 예외 - 가격이 0미만")
    @Test
    void validLessThanZero() {
        Assertions.assertThatThrownBy(() -> {
            Product.builder()
                    .title("투자상품")
                    .totalInvestingAmount(BigDecimal.valueOf(-1))
                    .startedAt(LocalDateTime.now())
                    .finishedAt(LocalDateTime.now().plusMonths(1))
                    .build();
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("상품 등록 예외 - 모집 시간")
    @Test
    void validateStartedAtAndFinishedAt() {
        Assertions.assertThatThrownBy(() -> {
            Product.builder()
                    .title("투자상품")
                    .totalInvestingAmount(BigDecimal.valueOf(100))
                    .startedAt(LocalDateTime.now().plusMonths(1))
                    .finishedAt(LocalDateTime.now())
                    .build();
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("상품 목록 모집기간 내 조회")
    @Test
    void findAllStartedAtAndFinishedAt() {
        LocalDateTime startAt = LocalDateTime.now();
        LocalDateTime finishAt = startAt.plusMonths(1);
        productRepository.saveAll(Arrays.asList(buildProduct(startAt, finishAt),buildProduct(startAt, finishAt)));

        List<Product> result = productRepository.findAllByStartedAtBeforeAndFinishedAtAfter(LocalDateTime.now(), LocalDateTime.now());
        assertThat(result.size()).isEqualTo(2);
    }
}
