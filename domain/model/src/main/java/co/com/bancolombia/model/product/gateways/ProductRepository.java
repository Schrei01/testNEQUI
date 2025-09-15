package co.com.bancolombia.model.product.gateways;

import co.com.bancolombia.model.product.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductRepository {
    Mono<Product> save(Product product);
    Mono<Product> findById(String id);
    Flux<Product> findByBranchId(String branchId);
    Flux<Product> findAll();
    Mono<Void> deleteById(String id);
}
