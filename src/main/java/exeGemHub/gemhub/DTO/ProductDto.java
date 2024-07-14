package exeGemHub.gemhub.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import exeGemHub.gemhub.Entity.Image;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private String productName;
    private String productType;
    private int productQuantity;
    private String productDescription;
    private float productPrice;
    private Date createTime;
    private Date updateTime;
    private List<Image> imgs = new ArrayList<>();
    private String theOrigin;
    private String component;
    private String stiffness;
    private String matchingDestiny;
    private String healthEffects;
    private String preserve;
    private boolean limited;
}
