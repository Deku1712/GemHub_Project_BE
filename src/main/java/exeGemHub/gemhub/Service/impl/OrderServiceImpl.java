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

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

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
    @Autowired
    private OrderDetailRepo orderDetailRepo;

    public OrderServiceImpl(ItemOfCartRepo itemOfCartRepo) {
        this.itemOfCartRepo = itemOfCartRepo;
    }

    @Transactional
    @Override
    public Order createOrder(PaymentMethod paymentMethod) {
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
        order.setCreateTime(Date.valueOf(LocalDate.now()));
        order.setUpdateTime(Date.valueOf(LocalDate.now()));



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
            order.setStatusPayment("Unpaid");
        } else if (paymentMethod.getPaymentMethod().equals("PAYWITHVNPAY")) {
            order.setStatus("DRAFT");
        }
        order.setStatusPayment("Unpaid");
        itemOfCartRepo.deleteAllInBatch(items);

        orderRepo.save(order);

        return orderRepo.save(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }

    @Override
    public Order getOrderById(int id) {
       return orderRepo.findById(id).get();
    }

    @Override
    public void updateOrder(int id, String statusOrder, String payment) {
        Order order = orderRepo.findById(id).get();
        order.setStatus(statusOrder);
        order.setStatusPayment(payment);
        order.setUpdateTime(Date.valueOf(LocalDate.now()));
        orderRepo.save(order);
    }

    @Override
    public void updateOrderStatus(int id, String status) {
        Order order = orderRepo.findById(id).get();
        order.setStatus(status);
        order.setUpdateTime(Date.valueOf(LocalDate.now()));
        orderRepo.save(order);
    }

    @Override
    public Map<Integer, Float> getMonthlyIncome() {
        List<Object[]> results = orderRepo.findMonthlyIncome();
        Map<Integer, Float> monthlyIncome = new HashMap<>();

        for (Object[] result : results) {
            Integer month = ((Number) result[0]).intValue();
            Float totalIncome = ((Number) result[1]).floatValue();
            monthlyIncome.put(month, totalIncome);
        }

        return monthlyIncome;
    }

    @Override
    public Float getTodayIncome() {
        return orderRepo.calculateTotalRevenueForToday();
    }


}
