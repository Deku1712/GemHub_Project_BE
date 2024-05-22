package exeGemHub.gemhub.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {

    private int productId;
    private int quantityOfProduct;
    private Boolean status;


}
