package co.com.bancolombia.mongo.helper;

import co.com.bancolombia.model.product.Product;
import co.com.bancolombia.mongo.MongoDBRepository;
import co.com.bancolombia.mongo.MongoRepositoryAdapter;
import co.com.bancolombia.mongo.data.ProductData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.data.domain.Example;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class AdapterOperationsTest {

    @Mock
    private MongoDBRepository repository;

    @Mock
    private ObjectMapper objectMapper;

    private MongoRepositoryAdapter adapter;

    private Product product;
    private ProductData productData;
    private Flux<ProductData> productDataFlux;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        productData = new ProductData();
        productData.setId("1");
        productData.setName("Test Product");
        productData.setPrice(100.0);
        productData.setStock(10);

        product = new Product();
        product.setName("Test Product");
        product.setPrice(100.0);
        product.setStock(10);

        when(objectMapper.map(productData, Product.class)).thenReturn(product);
        when(objectMapper.map(product, ProductData.class)).thenReturn(productData);

        adapter = new MongoRepositoryAdapter(repository, objectMapper);
    }

    @Test
    void testSave() {
        when(repository.save(productData)).thenReturn(Mono.just(productData));

        StepVerifier.create(adapter.save(product))
                .expectNext(product)
                .verifyComplete();
    }


    @Test
    void testSaveAll() {
        when(repository.saveAll(any(Flux.class))).thenReturn(Flux.just(productData));

        StepVerifier.create(adapter.saveAll(Flux.just(product)))
                .expectNext(product)
                .verifyComplete();
    }

    @Test
    void testFindById() {
        when(repository.findById("1")).thenReturn(Mono.just(productData));

        StepVerifier.create(adapter.findById("1"))
                .expectNext(product)
                .verifyComplete();
    }

    @Test
    void testFindByExample() {
        when(repository.findAll(any(Example.class))).thenReturn(Flux.just(productData));

        StepVerifier.create(adapter.findByExample(product))
                .expectNext(product)
                .verifyComplete();
    }

    @Test
    void testFindAll() {
        when(repository.findAll()).thenReturn(Flux.just(productData));

        StepVerifier.create(adapter.findAll())
                .expectNext(product)
                .verifyComplete();
    }

    @Test
    void testDeleteById() {
        when(repository.deleteById("1")).thenReturn(Mono.empty());

        StepVerifier.create(adapter.deleteById("1"))
                .verifyComplete();
    }
}
