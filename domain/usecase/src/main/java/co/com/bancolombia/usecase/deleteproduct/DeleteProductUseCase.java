package co.com.bancolombia.usecase.deleteproduct;

import co.com.bancolombia.model.product.gateways.ProductRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class DeleteProductUseCase {
    private final ProductRepository productRepository;

    public Mono<Void> execute(String branchId, String productId) {
        return productRepository.findById(productId)
                .filter(product -> product.getBranchId().equals(branchId))
                .flatMap(product -> productRepository.deleteById(productId));
    }
}
