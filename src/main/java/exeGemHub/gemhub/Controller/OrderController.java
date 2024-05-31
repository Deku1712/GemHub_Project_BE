package exeGemHub.gemhub.Controller;

import exeGemHub.gemhub.DTO.PaymentMethod;
import exeGemHub.gemhub.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderController {
    @Autowired
    public OrderService orderService;

    @PostMapping
    public boolean createOrder(@RequestBody PaymentMethod paymentMethod) {
        return orderService.createOrder(paymentMethod);
    }

}
