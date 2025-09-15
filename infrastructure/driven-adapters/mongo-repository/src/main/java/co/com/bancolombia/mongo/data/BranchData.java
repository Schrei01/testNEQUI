package co.com.bancolombia.mongo.data;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Document(collection = "branches")
public class BranchData {
    @Id
    private String id;
    private String franchiseId;
    private String name;
    private String address;
}
