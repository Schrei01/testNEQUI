package co.com.bancolombia.mongo;

import co.com.bancolombia.mongo.data.ProductData;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface MongoDBRepository extends ReactiveMongoRepository<ProductData, String> {
    Flux<ProductData> findByBranchId(String branchId);
    Flux<ProductData> findByFranchiseId(String franchiseId);
}
