package co.com.bancolombia.api;

import co.com.bancolombia.model.product.Product;
import co.com.bancolombia.usecase.createproduct.CreateProductUseCase;
import co.com.bancolombia.usecase.deleteproduct.DeleteProductUseCase;
import co.com.bancolombia.usecase.getallproducts.GetAllProductsUseCase;
import co.com.bancolombia.usecase.getproductbyid.GetProductByIdUseCase;
import co.com.bancolombia.usecase.updateproduct.UpdateProductUseCase;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebFluxTest
@ContextConfiguration(classes = {RouterRest.class, Handler.class})
class RouterRestTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private GetAllProductsUseCase getAllProductsUseCase;

    @MockBean
    private GetProductByIdUseCase getProductByIdUseCase;

    @MockBean
    private CreateProductUseCase createProductUseCase;

    @MockBean
    private UpdateProductUseCase updateProductUseCase;

    @MockBean
    private DeleteProductUseCase deleteProductUseCase;

    @Test
    void testGetAllProducts() {
        Product product = new Product();
        product.setName("Test");
        product.setPrice(10.0);
        product.setStock(5);

        when(getAllProductsUseCase.execute()).thenReturn(Flux.just(product));

        webTestClient.get()
                .uri("/api/products")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Product.class)
                .hasSize(1)
                .contains(product);
    }

    @Test
    void testGetProductById() {
        Product product = new Product();
        product.setName("Test");
        product.setPrice(10.0);
        product.setStock(5);

        when(getProductByIdUseCase.execute("1")).thenReturn(Mono.just(product));

        webTestClient.get()
                .uri("/api/products/1")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Product.class)
                .isEqualTo(product);
    }

    @Test
    void testCreateProduct() {
        Product product = new Product();
        product.setName("New Product");
        product.setPrice(20.0);
        product.setStock(10);

        when(createProductUseCase.execute(any(Product.class))).thenReturn(Mono.just(product));

        webTestClient.post()
                .uri("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(product)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Product.class)
                .isEqualTo(product);
    }

    @Test
    void testUpdateProduct() {
        Product product = new Product();
        product.setName("Updated Product");
        product.setPrice(30.0);
        product.setStock(15);

        when(updateProductUseCase.execute(Mockito.eq("1"), any(Product.class))).thenReturn(Mono.just(product));

        webTestClient.put()
                .uri("/api/products/1")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(product)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Product.class)
                .isEqualTo(product);
    }

    @Test
    void testDeleteProduct() {
        when(deleteProductUseCase.execute("1")).thenReturn(Mono.empty());

        webTestClient.delete()
                .uri("/api/products/1")
                .exchange()
                .expectStatus().isNoContent();
    }
}
