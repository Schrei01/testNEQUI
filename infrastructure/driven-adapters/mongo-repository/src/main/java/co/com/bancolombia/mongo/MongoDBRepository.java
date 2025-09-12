package co.com.bancolombia.mongo;

import co.com.bancolombia.mongo.data.ProductData;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoDBRepository extends ReactiveMongoRepository<ProductData, String> {
}
