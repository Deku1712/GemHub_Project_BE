package exeGemHub.gemhub.Controller;

import exeGemHub.gemhub.DTO.InforPayment;
import exeGemHub.gemhub.Repository.OrderRepo;
import exeGemHub.gemhub.Service.OrderService;
import exeGemHub.gemhub.Service.VNPayService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/payment")
@CrossOrigin
public class VnPayController {
    @Autowired
    private VNPayService vnPayService;
    @Autowired
    private OrderService orderService;

    @GetMapping("/")
    public String home() {
        return "Welcome to VNPay API";
    }

    @PostMapping("/submitOrder")
    public Map<String, String> submitOrder(@RequestBody InforPayment inforPayment,
                                           HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();

        String id = inforPayment.getOrderInfor();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("orderId".equals(cookie.getName())) {
                    id.concat(cookie.getValue());
//                    cookie.setMaxAge(0);
                    break;
                }
            }
        }
        String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        String vnpayUrl = vnPayService.createOrder(inforPayment.getAmount(), id, baseUrl);
        Map<String, String> response = new HashMap<>();
        response.put("redirectUrl", vnpayUrl);
        return response;
    }

//    @GetMapping("/vnpay-payment")
//    public Map<String, String> getPaymentStatus(HttpServletRequest request) {
//        int paymentStatus = vnPayService.orderReturn(request);
//
//        String orderInfo = request.getParameter("vnp_OrderInfo");
//        String paymentTime = request.getParameter("vnp_PayDate");
//        String transactionId = request.getParameter("vnp_TransactionNo");
//        String totalPrice = request.getParameter("vnp_Amount");
//
//        Map<String, String> response = new HashMap<>();
//        response.put("orderId", orderInfo);
//        response.put("totalPrice", totalPrice);
//        response.put("paymentTime", paymentTime);
//        response.put("transactionId", transactionId);
//        response.put("status", paymentStatus == 1 ? "success" : "fail");
//
//        return response;
//    }
    @GetMapping("/vnpay-payment")
    public RedirectView handleVNPayResponse(HttpServletRequest request) {


        // Xử lý các tham số trả về từ VNPay, ví dụ như kiểm tra vnp_SecureHash, cập nhật trạng thái đơn hàng, vv.

        // Chuyển hướng người dùng đến trang xác nhận thanh toán trên React với các tham số cần thiết
//        String redirectUrl = "https://gem-hub-project-fe.vercel.app/result?" + vnPayService.createQueryString(request) ;
        String redirectUrl = "http://localhost:5173/result?" + vnPayService.createQueryString(request) ;

        return new RedirectView(redirectUrl);

    }
}
