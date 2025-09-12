package co.com.bancolombia.api.config;

import co.com.bancolombia.api.Handler;
import co.com.bancolombia.api.RouterRest;
import co.com.bancolombia.usecase.createproduct.CreateProductUseCase;
import co.com.bancolombia.usecase.deleteproduct.DeleteProductUseCase;
import co.com.bancolombia.usecase.getallproducts.GetAllProductsUseCase;
import co.com.bancolombia.usecase.getproductbyid.GetProductByIdUseCase;
import co.com.bancolombia.usecase.updateproduct.UpdateProductUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {RouterRest.class, Handler.class})
@WebFluxTest
@Import({CorsConfig.class, SecurityHeadersConfig.class})
class ConfigTest {

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
    void corsConfigurationShouldAllowOrigins() {
        when(getAllProductsUseCase.execute()).thenReturn(Flux.empty());

        webTestClient.get()
                .uri("/api/products")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().valueEquals("Content-Security-Policy",
                        "default-src 'self'; frame-ancestors 'self'; form-action 'self'")
                .expectHeader().valueEquals("Strict-Transport-Security", "max-age=31536000;")
                .expectHeader().valueEquals("X-Content-Type-Options", "nosniff")
                .expectHeader().valueEquals("Server", "")
                .expectHeader().valueEquals("Cache-Control", "no-store")
                .expectHeader().valueEquals("Pragma", "no-cache")
                .expectHeader().valueEquals("Referrer-Policy", "strict-origin-when-cross-origin");
    }
}
