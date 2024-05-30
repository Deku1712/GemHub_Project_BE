package exeGemHub.gemhub.Service;

import exeGemHub.gemhub.DTO.ItemDto;
import exeGemHub.gemhub.Entity.Cart;
import exeGemHub.gemhub.Entity.ItemOfCart;

import java.util.List;
import java.util.Set;

public interface CartService {

    Cart getCartByUser();

    Cart addProductToCart(int id);

    Cart updateItem(ItemDto itemDto);

    Cart deleteItem(int id);

    void clearItem();

    boolean checkQuantity();

    Set<ItemOfCart> getOrder();
//
//    void increaseQuantity(int productId);
//
//    void decreaseQuantity(int productId);
}
