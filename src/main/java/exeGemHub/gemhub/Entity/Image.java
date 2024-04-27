package exeGemHub.gemhub.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "Image_product")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private  int id;

    @Column(name = "imgUrl")
    private String imgUrl;

    @ManyToOne
    @JoinColumn(name = "productId")
    @JsonIgnore
    private Product product;

}
