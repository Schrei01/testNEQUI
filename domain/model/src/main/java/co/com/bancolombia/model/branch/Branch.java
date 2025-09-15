package co.com.bancolombia.model.branch;
import co.com.bancolombia.model.product.Product;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Branch {
    private String id;
    private String franchiseId;
    private String name;
    private String address;
    @Builder.Default
    private List<Product> products = new ArrayList<>();
}
