package exeGemHub.gemhub.Controller;

import exeGemHub.gemhub.DTO.PaymentMethod;
import exeGemHub.gemhub.DTO.ResultDTO;
import exeGemHub.gemhub.Entity.Order;
import exeGemHub.gemhub.Service.OrderService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

}
