package exeGemHub.gemhub.Controller;

import exeGemHub.gemhub.DTO.ItemDto;
import exeGemHub.gemhub.Entity.Cart;
import exeGemHub.gemhub.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/user")
    public Cart getCartByUser(){
        return cartService.getCartByUser();
    }

    @PostMapping("/product/{id}")
    public void addItemToCart(@PathVariable("id") int id) {
        cartService.addProductToCart(id);
    }

    @PutMapping("/product")
    public void updateItem(@RequestBody ItemDto itemDto){
        cartService.updateItem(itemDto);
    }

    @DeleteMapping("/product/{id}")
    public void deleteItemInCart(@PathVariable("id") int id){
        cartService.deleteItem(id);
    }


}
