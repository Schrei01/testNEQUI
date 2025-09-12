package co.com.bancolombia.usecase.createproduct;

import co.com.bancolombia.model.product.Product;
import co.com.bancolombia.model.product.gateways.ProductRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class CreateProductUseCase {
    private final ProductRepository repository;

    public Mono<Product> execute(Product product) {
        return repository.save(product);
    }
}
