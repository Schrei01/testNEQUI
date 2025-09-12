package co.com.bancolombia.usecase.getallproducts;

import co.com.bancolombia.model.product.Product;
import co.com.bancolombia.model.product.gateways.ProductRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class GetAllProductsUseCase {
    private final ProductRepository repository;

    public Flux<Product> execute() {
        return repository.findAll();
    }
}
