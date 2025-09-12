package co.com.bancolombia.mongo.data;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "products")
public class ProductData {

    @Id
    private String id;
    private String name;
    private Double price;
    private int stock;
}
