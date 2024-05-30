package exeGemHub.gemhub.Repository;

import exeGemHub.gemhub.Entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepo extends JpaRepository<OrderDetail, Integer> {
}
