package invest;

import invest.domain.Invest;
import invest.domain.Product;
import invest.repository.InvestRepository;
import invest.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

@Component
@Profile("!test")
public class DataLoaderConfig implements CommandLineRunner {
    private ProductRepository productRepository;
    private InvestRepository investRepository;

    public DataLoaderConfig(ProductRepository productRepository, InvestRepository investRepository) {
        this.productRepository = productRepository;
        this.investRepository = investRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Product 개인신용포트폴리오 = Product.builder()
                .title("개인신용 포트폴리오")
                .totalInvestingAmount(BigDecimal.valueOf(1_000_000))
                .startedAt(LocalDateTime.now())
                .finishedAt(LocalDateTime.now().plusMonths(1))
                .build();


        Product 부동산포트폴리오 = Product.builder()
                .title("부동산 포트폴리오")
                .totalInvestingAmount(BigDecimal.valueOf(50_000_000))
                .startedAt(LocalDateTime.now())
                .finishedAt(LocalDateTime.now().plusMonths(1))
                .build();
        productRepository.saveAll(Arrays.asList(개인신용포트폴리오, 부동산포트폴리오));
        Invest 개인신용포트폴리오투자 = new Invest(BigDecimal.valueOf(1000), 1234L, 개인신용포트폴리오);
        Invest 부동산포트폴리오투자 = new Invest(BigDecimal.valueOf(4000), 1234L, 부동산포트폴리오);
        Invest 개인신용포트폴리오투자2 = new Invest(BigDecimal.valueOf(1000), 1235L, 개인신용포트폴리오);
        Invest 부동산포트폴리오투자2 = new Invest(BigDecimal.valueOf(4000), 1235L, 부동산포트폴리오);
        investRepository.saveAll(Arrays.asList(개인신용포트폴리오투자, 부동산포트폴리오투자, 개인신용포트폴리오투자2, 부동산포트폴리오투자2));

    }
}