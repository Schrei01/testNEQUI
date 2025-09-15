package co.com.bancolombia.mongo;

import co.com.bancolombia.model.branch.Branch;
import co.com.bancolombia.mongo.data.BranchData;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface BranchMongoDBRepository extends ReactiveMongoRepository<BranchData, String> {
    Flux<Branch> findByFranchiseId(String franchiseId);
}