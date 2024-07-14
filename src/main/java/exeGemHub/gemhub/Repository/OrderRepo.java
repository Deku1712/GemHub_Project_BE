package exeGemHub.gemhub.Repository;

import exeGemHub.gemhub.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order, Integer> {
    @Query("SELECT EXTRACT(MONTH FROM o.createTime) AS month, SUM(o.total) AS totalIncome " +
            "FROM Order o " +
            "WHERE o.status = 'SUCCESS' " +
            "GROUP BY EXTRACT(MONTH FROM o.createTime)")
    List<Object[]> findMonthlyIncome();

    @Query("SELECT COALESCE(SUM(o.total), 0) " +
            "FROM Order o " +
            "WHERE o.createTime = CURRENT_DATE")
    Float calculateTotalRevenueForToday();

    List<Order> findOrdersByUserId(int userId);
}
