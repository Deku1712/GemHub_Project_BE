package exeGemHub.gemhub.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InforPayment {
    private int amount;
    private String orderInfor;
}
