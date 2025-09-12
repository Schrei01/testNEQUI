package co.com.bancolombia.usecase.createproduct;

import co.com.bancolombia.model.product.Product;
import co.com.bancolombia.model.product.gateways.ProductRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class CreateProductUseCase {
    private final ProductRepository productRepository;

    public Mono<Product> execute(String branchId, Product product) {
        product.setBranchId(branchId);
        return productRepository.save(product);
    }
}
