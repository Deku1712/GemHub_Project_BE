package exeGemHub.gemhub.Service;

import exeGemHub.gemhub.DTO.ItemDto;
import exeGemHub.gemhub.Entity.Cart;

public interface CartService {

    Cart getCartByUser();

    Cart addProductToCart(int id);

    void updateItem(ItemDto itemDto);

    void deleteItem(int id);
}
