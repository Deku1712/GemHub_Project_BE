package exeGemHub.gemhub.Service;


import exeGemHub.gemhub.DTO.PaymentMethod;

public interface OrderService {

    boolean createOrder(PaymentMethod paymentMethod);

}
