package invest.service;

import invest.domain.Product;
import invest.dto.InvestRequest;
import invest.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
public class InvestServiceTest {
    private Product 개인상품_포트폴리오;

    @Autowired
    private InvestService investService;

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        Product product = new Product("개인상품 포트폴리오", BigDecimal.valueOf(100_000), LocalDateTime.now(), LocalDateTime.now().plusMonths(1));
        개인상품_포트폴리오 = productRepository.save(product);
    }

    @DisplayName("투자하기")
    @Test
    void invest() {
        InvestRequest investRequest = new InvestRequest(1L, BigDecimal.valueOf(1000), 1234L);
        investService.invest(investRequest);
    }

    @DisplayName("투자하기 예외 - 모집 완료된 상품 투자할 경우")
    @Test
    void validateSoldOutProduct() {
        개인상품_포트폴리오.changeCompleteStatus();
        InvestRequest investRequest = new InvestRequest(개인상품_포트폴리오.getId(), BigDecimal.valueOf(1000), 1234L);
        throwException(investRequest);
    }

    private void throwException(InvestRequest investRequest) {
        Assertions.assertThatThrownBy(() -> {
            investService.invest(investRequest);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("투자하기 예외 - 모집기간이 아닌 상품 투자할 경우")
    @Test
    void validateInvestingDate() {
        Product 모집예정인_포트폴리오  = productRepository.save(new Product("개인상품 포트폴리오", BigDecimal.valueOf(100_000), LocalDateTime.now().plusDays(10), LocalDateTime.now().plusMonths(1)));
        Product 모집이지난_포트폴리오 = productRepository.save(new Product("개인상품 포트폴리오", BigDecimal.valueOf(100_000), LocalDateTime.now().minusMonths(1), LocalDateTime.now().minusDays(1)));
        InvestRequest 모집예정인_포트폴리오_투자_요청 = new InvestRequest(모집예정인_포트폴리오.getId(), BigDecimal.valueOf(1_000), 1234L);
        InvestRequest 모집이지난_포트폴리오_투자_요청 = new InvestRequest(모집이지난_포트폴리오.getId(), BigDecimal.valueOf(1_000), 1234L);
        throwException(모집예정인_포트폴리오_투자_요청);
        throwException(모집이지난_포트폴리오_투자_요청);
    }

    @DisplayName("투자하기 예외 - 가능한 투자 금액을 초할 경우")
    @Test
    void validateOverAmount() {
        InvestRequest 가능한_투자_금액을_초과_투자_요청 = new InvestRequest(개인상품_포트폴리오.getId(), BigDecimal.valueOf(100_001), 1234L);
        throwException(가능한_투자_금액을_초과_투자_요청);
    }

    @DisplayName("투자하기 - 투자 가능한 금액과 현재 투자한 총 금액이 일치하면 모집 완료")
    @Test
    void investAfterChangeComplete() {
        Product product = productRepository.findAll().get(0);
        BigDecimal amount = product.getTotalInvestingAmount().subtract(product.getCurrentInvestingAmount());

        InvestRequest investRequest = new InvestRequest(product.getId(), amount, 1234L);
        investService.invest(investRequest);

        Product actual = productRepository.findById(product.getId()).get();
        assertThat(actual.isCompleted()).isTrue();
    }
}
