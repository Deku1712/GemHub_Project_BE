package exeGemHub.gemhub.Service;


import exeGemHub.gemhub.DTO.PaymentMethod;
import exeGemHub.gemhub.Entity.Order;

import java.util.List;

public interface OrderService {

    Order createOrder(PaymentMethod paymentMethod);

    List<Order> getAllOrders();

    Order getOrderById(int id);

    void updateOrder(int id, String statusOrder, String statusPayment);
}
