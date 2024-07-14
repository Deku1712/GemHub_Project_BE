package exeGemHub.gemhub.Controller;

import exeGemHub.gemhub.DTO.PaymentMethod;
import exeGemHub.gemhub.DTO.ResultDTO;
import exeGemHub.gemhub.DTO.Status;
import exeGemHub.gemhub.Entity.Order;
import exeGemHub.gemhub.Service.OrderService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderController {
    @Autowired
    public OrderService orderService;

    @GetMapping
    public List<Order> getAllOrders() {
        return  orderService.getAllOrders();
    }

    @GetMapping("/user")
    public List<Order> getOrderByUser() {
        return  orderService.getOrderByUser();
    }

    @PostMapping
    public Order createOrder(@RequestBody PaymentMethod paymentMethod ) {
        return  orderService.createOrder(paymentMethod);
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable("id") int id) {
        return orderService.getOrderById(id);
    }

    @PutMapping
    public void updateOrder(@RequestBody ResultDTO resultDTO) {

        if (resultDTO.getResult() == 1) {
            orderService.updateOrder(resultDTO.getOrderId(), "PROCESSING" , "PAID");
        }else {
            orderService.updateOrder(resultDTO.getOrderId(), "FAID" , "UNPAID");
        }
    }
    @PutMapping("/{id}")
    public void updateOrder(@PathVariable("id") int id, @RequestBody Status status) {
        orderService.updateOrderStatus(id , status.getStatus());
    }

    @GetMapping("/monthlyIncome")
    public Map<Integer, Float> getMonthlyIncome() {
        return orderService.getMonthlyIncome();
    }
    @GetMapping("/getTodayIncome")
    public float getTodayIncome() {
        return orderService.getTodayIncome();
    }

}
