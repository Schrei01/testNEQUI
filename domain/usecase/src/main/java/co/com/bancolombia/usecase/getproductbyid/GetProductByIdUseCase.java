package co.com.bancolombia.usecase.getproductbyid;

import co.com.bancolombia.model.product.Product;
import co.com.bancolombia.model.product.gateways.ProductRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class GetProductByIdUseCase {
    private final ProductRepository repository;

    public Mono<Product> execute(String id) {
        return repository.findById(id);
    }
}
