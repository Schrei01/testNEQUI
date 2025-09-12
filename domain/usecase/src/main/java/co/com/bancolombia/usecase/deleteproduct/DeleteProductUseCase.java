package co.com.bancolombia.usecase.deleteproduct;

import co.com.bancolombia.model.product.gateways.ProductRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class DeleteProductUseCase {
    private final ProductRepository repository;

    public Mono<Void> execute(String id) {
        return repository.deleteById(id);
    }
}
