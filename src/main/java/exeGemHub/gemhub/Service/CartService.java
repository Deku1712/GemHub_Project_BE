package exeGemHub.gemhub.Service;

import exeGemHub.gemhub.DTO.ItemDto;
import exeGemHub.gemhub.Entity.Cart;

public interface CartService {

    Cart getCartByUser();

    Cart addProductToCart(int id);

    Cart updateItem(ItemDto itemDto);

    void deleteItem(int id);

    void increaseQuantity(int productId);

    void decreaseQuantity(int productId);
}
