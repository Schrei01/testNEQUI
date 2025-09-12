package co.com.bancolombia.usecase.updateproduct;

import co.com.bancolombia.model.product.Product;
import co.com.bancolombia.model.product.gateways.ProductRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class UpdateProductUseCase {
    private final ProductRepository repository;

    public Mono<Product> execute(String id, Product product) {
        return repository.findById(id)
                .flatMap(existing -> {
                    existing.setName(product.getName());
                    existing.setPrice(product.getPrice());
                    existing.setStock(product.getStock());
                    return repository.save(existing);
                });
    }
}
