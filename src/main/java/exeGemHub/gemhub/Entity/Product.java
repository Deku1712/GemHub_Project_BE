package exeGemHub.gemhub.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @Column(name = "productDescription", columnDefinition = "NVARCHAR(250)")
    private String productDescription;
    @Column(name = "productPrice")
    private float productPrice;
    @Column(name = "createTime")
    @JsonFormat(shape = JsonFormat.Shape.STRING , pattern = "dd-MM-yyyy")
    private Date createTime;
    @Column(name = "updateTime")
    @JsonFormat(shape = JsonFormat.Shape.STRING , pattern = "dd-MM-yyyy")
    private Date updateTime;

    @OneToMany(mappedBy = "product" , cascade = CascadeType.ALL)
    private List<Image> imgs = new ArrayList<>();

    @Column(name = "theOrigin")
    private String theOrigin;
    @Column(name = "component")
    private String component;
    @Column(name = "stiffness")
    private String stiffness;
    @Column(name = "matchingDestiny")
    private String matchingDestiny;
    @Column(name = "healthEffects")
    private String healthEffects;
    @Column(name = "preserve")
    private String preserve;
    @Column(name ="limited")
    private boolean limited;
}
