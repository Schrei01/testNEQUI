package co.com.bancolombia.model.product;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Product {
    private String name;
    private double price;
    private int stock;
}
