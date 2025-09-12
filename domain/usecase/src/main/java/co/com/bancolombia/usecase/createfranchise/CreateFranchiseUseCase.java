package co.com.bancolombia.usecase.createfranchise;

import co.com.bancolombia.model.franchise.Franchise;
import co.com.bancolombia.model.franchise.gateways.FranchiseRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class CreateFranchiseUseCase {
    private final FranchiseRepository franchiseRepository;

    public Mono<Franchise> execute(Franchise franchise) {
        return franchiseRepository.save(franchise);
    }
}
