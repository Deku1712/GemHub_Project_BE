package exeGemHub.gemhub.Repository;

import exeGemHub.gemhub.Entity.Cart;
import exeGemHub.gemhub.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepo extends JpaRepository<Cart, Integer> {

    Cart findCartByUser(User user);
}
