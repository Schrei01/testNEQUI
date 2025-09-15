package co.com.bancolombia.usecase.gettopproductsbybranch;

import co.com.bancolombia.model.branch.gateways.BranchRepository;
import co.com.bancolombia.model.product.Product;
import co.com.bancolombia.model.product.gateways.ProductRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import java.util.Comparator;

@RequiredArgsConstructor
public class GetTopProductsByBranchUseCase {
    private final BranchRepository branchRepository;
    private final ProductRepository productRepository;

    public Flux<Product> execute(String franchiseId) {
        return branchRepository.findByFranchiseId(franchiseId)
                .flatMap(branch -> productRepository.findByBranchId(branch.getId())
                        .collectList()
                        .flatMapMany(products -> {
                            if (products.isEmpty()) {
                                return Flux.empty();
                            }
                            // Encontrar el producto con mayor stock en esta sucursal
                            Product maxStockProduct = products.stream()
                                    .max(Comparator.comparingInt(Product::getStock))
                                    .orElse(null);
                            return maxStockProduct != null ? Flux.just(maxStockProduct) : Flux.empty();
                        })
                );
    }
}
