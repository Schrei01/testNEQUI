package co.com.bancolombia.mongo;

import co.com.bancolombia.model.franchise.Franchise;
import co.com.bancolombia.model.franchise.gateways.FranchiseRepository;
import co.com.bancolombia.mongo.data.FranchiseData;
import co.com.bancolombia.mongo.helper.AdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

@Repository
public class MongoFranchiseAdapter extends AdapterOperations<Franchise, FranchiseData, String, FranchiseMongoDBRepository>
        implements FranchiseRepository {

    public MongoFranchiseAdapter(FranchiseMongoDBRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, Franchise.class));
    }
}
