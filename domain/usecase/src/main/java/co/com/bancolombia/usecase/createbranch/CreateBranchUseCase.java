package co.com.bancolombia.usecase.createbranch;

import co.com.bancolombia.model.branch.Branch;
import co.com.bancolombia.model.branch.gateways.BranchRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class CreateBranchUseCase {
    private final BranchRepository branchRepository;

    public Mono<Branch> execute(String franchiseId, Branch branch) {
        branch.setFranchiseId(franchiseId);
        return branchRepository.save(branch);
    }
}
