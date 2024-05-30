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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

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
            findItem.get().setStatus(true);

        }
        else {
            ItemOfCart newItem = new ItemOfCart();
            newItem.setProduct(product);
            newItem.setQuantity(1);
            newItem.setCart(cartByUser);
            newItem.setStatus(true);
            itemOfCarts.add(newItem);
        }
        cartByUser.setItems(itemOfCarts);
        return cartRepo.save(cartByUser);

    }




    @Override
    public Cart updateItem(ItemDto itemDto) {
        Product p = productRepo.findById(itemDto.getProductId()).get();
        Cart cart = getCartByUser();
        Set<ItemOfCart> itemsOfCarts = cart.getItems();
        Optional<ItemOfCart> findItem = itemsOfCarts.stream().filter(item -> item.getProduct().getId() == p.getId()).findFirst();
        if(findItem.isPresent()) {
            ItemOfCart itemOfCart = findItem.get();
            if(itemOfCart.getProduct().getProductQuantity() > itemDto.getQuantityOfProduct()) {
                itemOfCart.setQuantity(itemDto.getQuantityOfProduct());
            }
            itemOfCart.setStatus(itemDto.getStatus());
            

        }

        cart.setItems(itemsOfCarts);
        cartRepo.save(cart);
        return cart;
    }

    @Override
    public Cart deleteItem(int id) {
        itemOfCartRepo.deleteById(id);
        return getCartByUser();
    }

    @Override
    public void clearItem() {
        List<ItemOfCart> itemOfCarts = new ArrayList<>(getOrder()); // Chuyển từ Set sang List
        try {
            System.out.println("Đang xóa các mặt hàng...");
            for (ItemOfCart item : itemOfCarts) {
                System.out.println("hi "+item.getId());
                if (itemOfCartRepo.existsById(item.getId())) {
                    itemOfCartRepo.deleteById(item.getId());
                    System.out.println("Đã xóa mặt hàng với ID = " + item.getId());
                } else {
                    System.out.println("Không tìm thấy mặt hàng với ID = " + item.getId());
                }
            }
            // Kiểm tra xem các mặt hàng đã được xóa thành công hay không
            for (ItemOfCart item : itemOfCarts) {
                if (!itemOfCartRepo.existsById(item.getId())) {
                    System.out.println("Xác nhận: Mặt hàng với ID = " + item.getId() + " đã được xóa.");
                } else {
                    System.out.println("Lỗi: Mặt hàng với ID = " + item.getId() + " vẫn tồn tại.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Ngoại lệ trong clearItem: " + e.getMessage());
        }
    }


    @Override
    public boolean checkQuantity() {
        boolean isUpdated = true;
        Cart cart = getCartByUser();
        Set<ItemOfCart> itemOfCartList = cart.getItems();

        for (ItemOfCart itemOfCart : itemOfCartList) {
            System.out.println(itemOfCart.getProduct().getProductQuantity());
            System.out.println(itemOfCart.getQuantity());
            if (itemOfCart.getProduct().getProductQuantity() < itemOfCart.getQuantity()) {
                itemOfCart.setQuantity(itemOfCart.getProduct().getProductQuantity());
                itemOfCart.setStatus(false);
                isUpdated = false;
            }
        }

        if (isUpdated) {
            cartRepo.save(cart);
        }

        return isUpdated;
    }

    @Override
    public Set<ItemOfCart> getOrder() {
        Cart cart = getCartByUser();
        Set<ItemOfCart> cartItems = cart.getItems();
        return cartItems.stream().filter(itemOfCart -> itemOfCart.getStatus() == true).collect(Collectors.toSet());



    }

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
