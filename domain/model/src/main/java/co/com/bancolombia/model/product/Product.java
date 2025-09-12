package co.com.bancolombia.model.product;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Product {
    private String id;
    private String branchId;
    private String name;
    private double price;
    private int stock;
}
