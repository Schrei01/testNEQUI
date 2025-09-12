package co.com.bancolombia.api;

import co.com.bancolombia.model.branch.Branch;
import co.com.bancolombia.model.franchise.Franchise;
import co.com.bancolombia.model.product.Product;
import co.com.bancolombia.usecase.createbranch.CreateBranchUseCase;
import co.com.bancolombia.usecase.createfranchise.CreateFranchiseUseCase;
import co.com.bancolombia.usecase.createproduct.CreateProductUseCase;
import co.com.bancolombia.usecase.deleteproduct.DeleteProductUseCase;
import co.com.bancolombia.usecase.getallproducts.GetAllProductsUseCase;
import co.com.bancolombia.usecase.getproductbyid.GetProductByIdUseCase;
import co.com.bancolombia.usecase.gettopproductsbybranch.GetTopProductsByBranchUseCase;
import co.com.bancolombia.usecase.updateproduct.UpdateProductUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromPublisher;

@Component
@RequiredArgsConstructor
public class Handler {
    private final CreateFranchiseUseCase createFranchiseUseCase;
    private final CreateBranchUseCase createBranchUseCase;
    private final CreateProductUseCase createProductUseCase;
    private final DeleteProductUseCase deleteProductUseCase;
    private final UpdateProductUseCase updateProductUseCase;
    private final GetTopProductsByBranchUseCase getTopProductsByBranchUseCase;

    // 1. Agregar una nueva franquicia
    public Mono<ServerResponse> createFranchise(ServerRequest request) {
        return request.bodyToMono(Franchise.class)
                .flatMap(createFranchiseUseCase::execute)
                .flatMap(franchise -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(franchise));
    }

    // 2. Agregar una nueva sucursal a una franquicia
    public Mono<ServerResponse> createBranch(ServerRequest request) {
        String franchiseId = request.pathVariable("franchiseId");
        return request.bodyToMono(Branch.class)
                .flatMap(branch -> createBranchUseCase.execute(franchiseId, branch))
                .flatMap(branch -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(branch));
    }

    // 3. Agregar un nuevo producto a una sucursal
    public Mono<ServerResponse> createProduct(ServerRequest request) {
        String branchId = request.pathVariable("branchId");
        return request.bodyToMono(Product.class)
                .flatMap(product -> createProductUseCase.execute(branchId, product))
                .flatMap(product -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(product));
    }

    // 4. Eliminar un producto de una sucursal
    public Mono<ServerResponse> deleteProduct(ServerRequest request) {
        String branchId = request.pathVariable("branchId");
        String productId = request.pathVariable("productId");
        return deleteProductUseCase.execute(branchId, productId)
                .then(ServerResponse.noContent().build());
    }

    // 5. Modificar stock de un producto
    public Mono<ServerResponse> updateProductStock(ServerRequest request) {
        String branchId = request.pathVariable("branchId");
        String productId = request.pathVariable("productId");
        return request.bodyToMono(Product.class)
                .flatMap(product -> updateProductUseCase.execute(branchId, productId, product.getStock()))
                .flatMap(product -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(product));
    }

    // 6. Producto con más stock por sucursal de una franquicia
    public Mono<ServerResponse> getMaxStockProducts(ServerRequest request) {
        String franchiseId = request.pathVariable("franchiseId");
        return getTopProductsByBranchUseCase.execute(franchiseId)
                .collectList()
                .flatMap(products -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(products));
    }
}
