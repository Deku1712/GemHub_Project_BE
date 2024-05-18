package exeGemHub.gemhub.Controller;

import exeGemHub.gemhub.DTO.InforPayment;
import exeGemHub.gemhub.Service.VNPayService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/payment")
@CrossOrigin("*")
public class VnPayController {
    @Autowired
    private VNPayService vnPayService;

    @GetMapping("/")
    public String home() {
        return "Welcome to VNPay API";
    }

    @PostMapping("/submitOrder")
    public Map<String, String> submitOrder(@RequestBody InforPayment inforPayment,
                                           HttpServletRequest request) {
        String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        String vnpayUrl = vnPayService.createOrder(inforPayment.getAmount(), inforPayment.getOrderInfor(), baseUrl);
        Map<String, String> response = new HashMap<>();
        response.put("redirectUrl", vnpayUrl);
        return response;
    }

    @GetMapping("/vnpay-payment")
    public Map<String, String> getPaymentStatus(HttpServletRequest request) {
        int paymentStatus = vnPayService.orderReturn(request);

        String orderInfo = request.getParameter("vnp_OrderInfo");
        String paymentTime = request.getParameter("vnp_PayDate");
        String transactionId = request.getParameter("vnp_TransactionNo");
        String totalPrice = request.getParameter("vnp_Amount");

        Map<String, String> response = new HashMap<>();
        response.put("orderId", orderInfo);
        response.put("totalPrice", totalPrice);
        response.put("paymentTime", paymentTime);
        response.put("transactionId", transactionId);
        response.put("status", paymentStatus == 1 ? "success" : "fail");

        return response;
    }
}
