package co.com.bancolombia.mongo;

import co.com.bancolombia.mongo.data.FranchiseData;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface FranchiseMongoDBRepository extends ReactiveMongoRepository<FranchiseData, String> {

}
