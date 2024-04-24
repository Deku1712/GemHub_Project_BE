package exeGemHub.gemhub.Repository;

import exeGemHub.gemhub.Entity.Cart;
import exeGemHub.gemhub.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepo extends JpaRepository<Cart, Integer> {

    Cart findCartByUser(User user);
}
