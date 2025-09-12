package co.com.bancolombia.model.branch;
import co.com.bancolombia.model.product.Product;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
//import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
//@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Branch {
    private String name;
    private String address;
    @Builder.Default
    private List<Product> products = new ArrayList<>();
}
