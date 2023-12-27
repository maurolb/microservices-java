package mauro.shopping.shopping.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Customer {
    private Long id;
    private String firstName;
    private String lastName;
    private String numberID;
    private String email;
    private String photoUrl;
    private String state;
    private Region region;
}
