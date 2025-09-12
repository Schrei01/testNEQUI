package co.com.bancolombia.usecase.gettopproductsbybranch;

import co.com.bancolombia.model.product.Product;
import co.com.bancolombia.model.product.gateways.ProductRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class GetTopProductsByBranchUseCase {
    private final ProductRepository productRepository;

    public Flux<Product> execute(String franchiseId) {
        return productRepository.findByFranchiseId(franchiseId)
                .groupBy(Product::getBranchId)
                .flatMap(group -> group.sort((p1, p2) -> Integer.compare(p2.getStock(), p1.getStock()))
                        .next());
    }
}
