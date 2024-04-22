package exeGemHub.gemhub.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "productName")
    private String productName;
    @Column(name = "productType")
    private String productType;
    @Column(name = "productQuantity")
    private int productQuantity;
    @Column(name = "productDescription")
    private String productDescription;
    @Column(name = "productPrice")
    private float productPrice;
    @Column(name = "createTime")
    private Date createTime;
    @Column(name = "updateTime")
    private Date updateTime;

    @OneToMany(mappedBy = "product")
    private List<Image> imgs = new ArrayList<>();

}
