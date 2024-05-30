package exeGemHub.gemhub.Repository;

import exeGemHub.gemhub.Entity.Cart;
import exeGemHub.gemhub.Entity.ItemOfCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemOfCartRepo extends JpaRepository<ItemOfCart, Integer> {


    void deleteById(int id);
}
