package exeGemHub.gemhub.Service.impl;

import exeGemHub.gemhub.DTO.ItemDto;
import exeGemHub.gemhub.Entity.Cart;
import exeGemHub.gemhub.Entity.Product;
import exeGemHub.gemhub.Entity.User;
import exeGemHub.gemhub.Repository.CartRepo;
import exeGemHub.gemhub.Repository.ProductRepo;
import exeGemHub.gemhub.Repository.UserRepo;
import exeGemHub.gemhub.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
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
        User user = userRepo.findById(1).get();
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
            User user = userRepo.findById(1).get(); // just example.
            Cart newCart = new Cart();
            newCart.setUser(user);
            Map<Product , Integer> items = newCart.getItems();
            items.put(product, 1);
            newCart.setItems(items);
            return cartRepo.save(newCart);
        }
    }

    @Override
    public void updateItem(ItemDto itemDto) {
        Product p = productRepo.findById(itemDto.getProductId()).get();
        Cart cart = getCartByUser();
        Map<Product, Integer> items = cart.getItems();
        items.computeIfPresent(p , (k,v) -> itemDto.getQuantityOfProduct());
        cart.setItems(items);
        cartRepo.save(cart);
    }

    @Override
    public void deleteItem(int id) {
        Product p = productRepo.findById(id).get();
        Cart cart = getCartByUser();
        Map<Product,Integer> items = cart.getItems();
        items.computeIfPresent(p , (k,v) -> null);
    }
}
