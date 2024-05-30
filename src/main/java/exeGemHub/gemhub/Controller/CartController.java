package exeGemHub.gemhub.Controller;

import exeGemHub.gemhub.DTO.ItemDto;
import exeGemHub.gemhub.Entity.Cart;
import exeGemHub.gemhub.Entity.ItemOfCart;
import exeGemHub.gemhub.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/user")
    public Cart getCartByUser(){
        return cartService.getCartByUser();
    }

    @PostMapping("/product/{id}")
    public Cart addItemToCart(@PathVariable("id") int id) {
        return cartService.addProductToCart(id);
    }

    @PutMapping("/product")
    public Cart updateItem(@RequestBody ItemDto itemDto){
        return cartService.updateItem(itemDto);
    }
//
    @DeleteMapping("/items/{id}")
    public Cart deleteItemInCart(@PathVariable("id") int id){
        return cartService.deleteItem(id);
    }

    @GetMapping("/checkQuantity")
    public boolean checkQuantity(){
        return cartService.checkQuantity();
    }

    @GetMapping("/getOrder")
    public Set<ItemOfCart> getOrder(){
        return  cartService.getOrder();
    }



}
