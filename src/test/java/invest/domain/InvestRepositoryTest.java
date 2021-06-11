package invest.domain;

import invest.repository.InvestRepository;
import invest.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class InvestRepositoryTest {
    @Autowired
    private InvestRepository investRepository;

    @Autowired
    private ProductRepository productRepository;

    @DisplayName("등록")
    @Test
    void create() {
        Product product = productRepository.save(new Product("투자상품", BigDecimal.valueOf(1000), LocalDateTime.now(), LocalDateTime.now().plusMonths(1)));
        Invest expected = new Invest(BigDecimal.valueOf(3000), 12345L, product);
        Invest invest = investRepository.save(expected);
        assertThat(expected == invest).isTrue();
    }

}
