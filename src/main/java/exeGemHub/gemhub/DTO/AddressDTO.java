package exeGemHub.gemhub.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddressDTO {

    private String name;
    private String phone;
    private String address;
    private String description;
    private String province;
    private String city;
    private String wrap;

}
