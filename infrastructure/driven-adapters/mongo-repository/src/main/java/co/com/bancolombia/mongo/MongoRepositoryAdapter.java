package co.com.bancolombia.mongo;

import co.com.bancolombia.model.product.Product;
import co.com.bancolombia.model.product.gateways.ProductRepository;
import co.com.bancolombia.mongo.data.ProductData;
import co.com.bancolombia.mongo.helper.AdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public class MongoRepositoryAdapter extends AdapterOperations<Product, ProductData, String, MongoDBRepository>
        implements ProductRepository {

    public MongoRepositoryAdapter(MongoDBRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, Product.class));
    }

    @Override
    public Flux<Product> findByBranchId(String branchId) {
        return repository.findByBranchId(branchId)
                .map(entity -> mapper.map(entity, Product.class));
    }

    @Override
    public Flux<Product> findByFranchiseId(String franchiseId) {
        return repository.findByFranchiseId(franchiseId)
                .map(entity -> mapper.map(entity, Product.class));
    }
}
