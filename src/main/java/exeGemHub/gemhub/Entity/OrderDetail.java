package exeGemHub.gemhub.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
    @JsonIgnore
    @ToString.Exclude
    private Order order;
    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;
    @Column(name = "productQuantity")
    private int productQuantity;
    @Column(name = "productPrice")
    private float productPrice;
    @Column(name = "total")
    private float total;
}
