package co.com.bancolombia.usecase.updateproduct;

import co.com.bancolombia.model.product.Product;
import co.com.bancolombia.model.product.gateways.ProductRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class UpdateProductUseCase {
    private final ProductRepository productRepository;

    public Mono<Product> execute(String branchId, String productId, int newStock) {
        return productRepository.findById(productId)
                .filter(product -> product.getBranchId().equals(branchId))
                .flatMap(product -> {
                    product.setStock(newStock);
                    return productRepository.save(product);
                });
    }
}
