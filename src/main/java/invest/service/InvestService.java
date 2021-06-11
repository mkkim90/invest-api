package invest.service;

import invest.domain.Invest;
import invest.domain.Product;
import invest.dto.InvestRequest;
import invest.dto.InvestResponse;
import invest.repository.InvestRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class InvestService {
    private final InvestRepository investRepository;
    private final ProductService productService;

    public InvestService(final InvestRepository investRepository, final ProductService productService) {
        this.investRepository = investRepository;
        this.productService = productService;
    }

    @Transactional(readOnly = true)
    public List<InvestResponse> list(Long userId) {
        List<Invest> invests = investRepository.findAllByUserId(userId);
        if (invests.size() == 0) throw new IllegalArgumentException("조회되는 투자상품이 없습니다.");
        return invests.stream()
                .map(InvestResponse::of)
                .collect(Collectors.toList());
    }

    public InvestResponse invest(InvestRequest investRequest) {
        Product product = productService.findById(investRequest.getProductId());
        Invest savedInvest = new Invest(investRequest.getAmount(), investRequest.getUserId(), product);
        product.invest(savedInvest);
        investRepository.save(savedInvest);
        if (product.isSoldOutTotalInvestingAmount()) {
            productService.changProductStatusCompletionByInvesting(product.getId());
        }
        return InvestResponse.of(savedInvest);
    }

    @Transactional(readOnly = true)
    public InvestResponse findById(Long id) {
        Invest invest = investRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        return InvestResponse.of(invest);
    }
}