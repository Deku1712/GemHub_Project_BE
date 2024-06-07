package exeGemHub.gemhub.Repository;

import exeGemHub.gemhub.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
    List<Product> findByProductNameContainingIgnoreCase(String word);
    List<Product> findByLimited(Boolean limited);
}
