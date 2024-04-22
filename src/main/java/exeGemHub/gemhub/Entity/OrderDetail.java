package exeGemHub.gemhub.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name ="OrderDetail")
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "orderId")
    private Order order;
    @Column(name = "productName")
    private String productName;
    @Column(name = "productQuantity")
    private int productQuantity;
    @Column(name = "productPrice")
    private float productPrice;
    @Column(name = "total")
    private float total;
}
