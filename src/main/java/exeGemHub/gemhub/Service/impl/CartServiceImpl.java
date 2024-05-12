package exeGemHub.gemhub.Service.impl;

import exeGemHub.gemhub.DTO.ItemDto;
import exeGemHub.gemhub.Entity.Cart;
import exeGemHub.gemhub.Entity.Product;
import exeGemHub.gemhub.Entity.User;
import exeGemHub.gemhub.Entity.UserPrinciple;
import exeGemHub.gemhub.Repository.CartRepo;
import exeGemHub.gemhub.Repository.ProductRepo;
import exeGemHub.gemhub.Repository.UserRepo;
import exeGemHub.gemhub.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private UserRepo userRepo;



    @Autowired
    private ProductRepo productRepo;
    @Override
    public Cart getCartByUser() {
        SecurityContext contextHolder = SecurityContextHolder.getContext();
        Authentication authentication = contextHolder.getAuthentication();
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        String username = userPrinciple.getUsername();
        User user = userRepo.findByUsername(username);
        return cartRepo.findCartByUser(user);
    }

    @Override
    public Cart addProductToCart(int id) {
        Product product = productRepo.findById(id).get();
        Cart cart = getCartByUser();
        if(cart != null ){
            Map<Product , Integer> items = cart.getItems();
            if(items.containsKey(product)){
                items.computeIfPresent(product, (k,v) -> v+1);
            }
            else {
                items.put(product, 1);
            }
            cart.setItems(items);
            return cartRepo.save(cart);
        }
        else {
            SecurityContext contextHolder = SecurityContextHolder.getContext();
            Authentication authentication = contextHolder.getAuthentication();
            UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
            String username = userPrinciple.getUsername();
            User user = userRepo.findByUsername(username);
            Cart newCart = new Cart();
            newCart.setUser(user);
            Map<Product , Integer> items = newCart.getItems();
            items.put(product, 1);
            newCart.setItems(items);
            return cartRepo.save(newCart);
        }
    }

    @Override
    public Cart updateItem(ItemDto itemDto) {
        Product p = productRepo.findById(itemDto.getProductId()).get();
        Cart cart = getCartByUser();
        Map<Product, Integer> items = cart.getItems();
        items.computeIfPresent(p , (k,v) -> itemDto.getQuantityOfProduct());
        cart.setItems(items);
        cartRepo.save(cart);
        return cart;
    }

    @Override
    public void deleteItem(int id) {
        Product p = productRepo.findById(id).get();
        Cart cart = getCartByUser();
        Map<Product,Integer> items = cart.getItems();
        items.computeIfPresent(p , (k,v) -> null);
    }

    @Override
    public void increaseQuantity(int productId) {
//        Product p = productRepo.findById(productId).get();
//        Cart cart = getCartByUser();
//        Map<Product, Integer> items = cart.getItems();
//        items.computeIfPresent(p, (k,v) -> v+1);
    }

    @Override
    public void decreaseQuantity(int productId) {
//        Product p = productRepo.findById(productId).get();
//        Cart cart = getCartByUser();
//        Map<Product, Integer> items = cart.getItems();
//        items.computeIfPresent(p, (k,v) -> v-1);
    }
}
