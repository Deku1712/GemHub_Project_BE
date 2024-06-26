package exeGemHub.gemhub.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne
    @JoinColumn(name = "userId")
    @JsonIgnore
    private User user;

//    @ElementCollection
//    @CollectionTable(name = "cart_items", joinColumns = @JoinColumn(name = "cart_id"))
//    @MapKeyJoinColumn(name = "product_id")
//    @Column(name = "quantity")
//    private Map<Product, Integer> items = new HashMap<>();

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private Set<ItemOfCart> items = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return id == cart.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }



}
