package co.com.bancolombia.api;

import co.com.bancolombia.model.branch.Branch;
import co.com.bancolombia.model.franchise.Franchise;
import co.com.bancolombia.model.product.Product;
import co.com.bancolombia.usecase.createbranch.CreateBranchUseCase;
import co.com.bancolombia.usecase.createfranchise.CreateFranchiseUseCase;
import co.com.bancolombia.usecase.createproduct.CreateProductUseCase;
import co.com.bancolombia.usecase.deleteproduct.DeleteProductUseCase;
import co.com.bancolombia.usecase.gettopproductsbybranch.GetTopProductsByBranchUseCase;
import co.com.bancolombia.usecase.updateproduct.UpdateProductUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;


@ContextConfiguration(classes = {RouterRest.class, Handler.class})
@WebFluxTest
class RouterRestTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private CreateFranchiseUseCase createFranchiseUseCase;
    @MockBean
    private CreateBranchUseCase createBranchUseCase;
    @MockBean
    private CreateProductUseCase createProductUseCase;
    @MockBean
    private DeleteProductUseCase deleteProductUseCase;
    @MockBean
    private UpdateProductUseCase updateProductUseCase;
    @MockBean
    private GetTopProductsByBranchUseCase getMaxStockProductsUseCase;

    @Test
    void testCreateFranchise() {
        Franchise franchise = Franchise.builder()
                .id("1")
                .name("Franchise1")
                .branches(new ArrayList<>())
                .build();

        when(createFranchiseUseCase.execute(any(Franchise.class))).thenReturn(Mono.just(franchise));

        webTestClient.post()
                .uri("/api/franchises")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(franchise)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Franchise.class)
                .isEqualTo(franchise);
    }


    @Test
    void testCreateBranch() {
        Branch branch = Branch.builder()
                .id("1")
                .franchiseId("1")
                .name("Branch1")
                .address("Address1")
                .products(new ArrayList<>())
                .build();

        when(createBranchUseCase.execute(eq("1"), any(Branch.class))).thenReturn(Mono.just(branch));

        webTestClient.post()
                .uri("/api/franchises/1/branches")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(branch)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Branch.class)
                .isEqualTo(branch);
    }


    @Test
    void testCreateProduct() {
        Product product = Product.builder()
                .id("1")
                .branchId("1")
                .name("Product1")
                .price(10.0)
                .stock(5)
                .build();

        when(createProductUseCase.execute(eq("1"), any(Product.class))).thenReturn(Mono.just(product));

        webTestClient.post()
                .uri("/api/branches/1/products")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(product)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Product.class)
                .isEqualTo(product);
    }


    @Test
    void testDeleteProduct() {
        when(deleteProductUseCase.execute(eq("1"), eq("1"))).thenReturn(Mono.empty());

        webTestClient.delete()
                .uri("/api/branches/1/products/1")
                .exchange()
                .expectStatus().isNoContent();
    }


    @Test
    void testUpdateProductStock() {
        Product updated = Product.builder()
                .id("1")
                .branchId("1")
                .name("Product1")
                .price(10.0)
                .stock(20)
                .build();

        when(updateProductUseCase.execute(eq("1"), eq("1"), eq(20))).thenReturn(Mono.just(updated));

        webTestClient.put()
                .uri("/api/branches/1/products/1/stock")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(updated)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Product.class)
                .isEqualTo(updated);
    }



    @Test
    void testGetMaxStockProducts() {

        Product product = Product.builder()
                .id("1")
                .branchId("1")
                .name("Product1")
                .price(10.0)
                .stock(20)
                .build();

        when(getMaxStockProductsUseCase.execute(eq("1"))).thenReturn(Flux.just(product));

        webTestClient.get()
                .uri("/api/franchises/1/branches/products/max-stock")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Product.class)
                .hasSize(1)
                .contains(product);
    }
}
