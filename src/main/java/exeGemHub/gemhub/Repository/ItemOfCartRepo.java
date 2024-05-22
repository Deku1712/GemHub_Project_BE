package exeGemHub.gemhub.Repository;

import exeGemHub.gemhub.Entity.ItemOfCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemOfCartRepo extends JpaRepository<ItemOfCart, Integer> {
}
