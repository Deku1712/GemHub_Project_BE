package exeGemHub.gemhub.Service.impl;

import exeGemHub.gemhub.DTO.ItemDto;
import exeGemHub.gemhub.Entity.*;
import exeGemHub.gemhub.Repository.CartRepo;
import exeGemHub.gemhub.Repository.ItemOfCartRepo;
import exeGemHub.gemhub.Repository.ProductRepo;
import exeGemHub.gemhub.Repository.UserRepo;
import exeGemHub.gemhub.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ItemOfCartRepo itemOfCartRepo;



    @Autowired
    private ProductRepo productRepo;
    @Override
    public Cart getCartByUser() {
        SecurityContext contextHolder = SecurityContextHolder.getContext();
        Authentication authentication = contextHolder.getAuthentication();
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        String username = userPrinciple.getUsername();
        System.out.println(username);
        User user = userRepo.findByUsername(username);
        Cart cart = cartRepo.findCartByUser(user);
        if (cart == null) {
            Cart newCart = new Cart();
            newCart.setUser(user);
            return cartRepo.save(newCart);
        }
        else {
            return cart;
        }
    }

    @Override
    public Cart addProductToCart(int productId) {
        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Cart cartByUser = getCartByUser();
        Set<ItemOfCart> itemOfCarts = cartByUser.getItems();
        Optional<ItemOfCart> findItem = itemOfCarts.stream().filter(item -> item.getProduct().getId() == productId).findFirst();
        if(findItem.isPresent()) {
            findItem.get().setQuantity(findItem.get().getQuantity() + 1);
        }
        else {
            ItemOfCart newItem = new ItemOfCart();
            newItem.setProduct(product);
            newItem.setQuantity(1);
            newItem.setCart(cartByUser);
            newItem.setStatus(false);
            itemOfCarts.add(newItem);
        }
        cartByUser.setItems(itemOfCarts);
        return cartRepo.save(cartByUser);

    }



//
//    @Override
//    public Cart updateItem(ItemDto itemDto) {
//        Product p = productRepo.findById(itemDto.getProductId()).get();
//        Cart cart = getCartByUser();
//        Map<Product, Integer> items = cart.getItems();
//        items.computeIfPresent(p , (k,v) -> itemDto.getQuantityOfProduct());
//        cart.setItems(items);
//        cartRepo.save(cart);
//        return cart;
//    }
//
//    @Override
//    public void deleteItem(int id) {
//        Product p = productRepo.findById(id).get();
//        Cart cart = getCartByUser();
//        Map<Product,Integer> items = cart.getItems();
//        items.computeIfPresent(p , (k,v) -> null);
//    }
//
//    @Override
//    public void increaseQuantity(int productId) {
////        Product p = productRepo.findById(productId).get();
////        Cart cart = getCartByUser();
////        Map<Product, Integer> items = cart.getItems();
////        items.computeIfPresent(p, (k,v) -> v+1);
//    }
//
//    @Override
//    public void decreaseQuantity(int productId) {
////        Product p = productRepo.findById(productId).get();
////        Cart cart = getCartByUser();
////        Map<Product, Integer> items = cart.getItems();
////        items.computeIfPresent(p, (k,v) -> v-1);
//    }
}
