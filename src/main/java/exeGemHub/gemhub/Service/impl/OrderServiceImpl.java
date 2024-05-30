package exeGemHub.gemhub.Service.impl;

import exeGemHub.gemhub.DTO.PaymentMethod;
import exeGemHub.gemhub.Entity.*;
import exeGemHub.gemhub.Repository.*;
import exeGemHub.gemhub.Service.AddressService;
import exeGemHub.gemhub.Service.CartService;
import exeGemHub.gemhub.Service.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    public OrderRepo orderRepo;

    @Autowired
    private CartService cartService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private UserRepo userRepo;

    private final ItemOfCartRepo itemOfCartRepo;

    public OrderServiceImpl(ItemOfCartRepo itemOfCartRepo) {
        this.itemOfCartRepo = itemOfCartRepo;
    }

    @Transactional
    @Override
    public boolean createOrder(PaymentMethod paymentMethod) {
        try {
            SecurityContext contextHolder = SecurityContextHolder.getContext();
            Authentication authentication = contextHolder.getAuthentication();
            UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
            String username = userPrinciple.getUsername();
            System.out.println("Username: " + username);

            User user = userRepo.findByUsername(username);
            Address address = addressService.getAddressDefault();

            Order order = new Order();
            List<OrderDetail> orderDetails = new ArrayList<>();

            // Create new Order
            order.setUser(user);
            order.setOrderPhone(address.getPhone());
            order.setOrderAddress(address.getAddress());


            System.out.println("Order saved: " + order.getId());

            Set<ItemOfCart> items = cartService.getOrder();

            // Create each orderDetail
            for (ItemOfCart item : items) {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOrder(order);
                orderDetail.setProduct(item.getProduct());
                orderDetail.setProductPrice(item.getProduct().getProductPrice());
                orderDetail.setProductQuantity(item.getQuantity());
                orderDetail.setTotal(item.getQuantity() * item.getProduct().getProductPrice());
                orderDetails.add(orderDetail);
                System.out.println("OrderDetail added: " + item.getId());
            }

            // Calculate total price
            float totalPrice = orderDetails.stream()
                    .map(item -> item.getProductQuantity() * item.getProductPrice())
                    .reduce(0.0f, Float::sum);

            order.setTotal(totalPrice);
            order.setOrderDetails(orderDetails);
            System.out.println("payment method: " + paymentMethod);
            if(paymentMethod.getPaymentMethod().equals("PAYMENTONDELIVERY")) {
                order.setStatus("PROCESSING");
            } else if (paymentMethod.getPaymentMethod().equals("PAYWITHVNPAY")) {
                order.setStatus("DRAFT");
            }
            itemOfCartRepo.deleteAllInBatch(items);

            orderRepo.save(order);

            return true;


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception in createOrder: " + e.getMessage());
            return  false;
        }
    }




}
