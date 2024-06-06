package exeGemHub.gemhub.Repository;

import exeGemHub.gemhub.Entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepo extends JpaRepository<OrderDetail, Integer> {

    List<OrderDetail> findByOrderId(int orderId);

}
