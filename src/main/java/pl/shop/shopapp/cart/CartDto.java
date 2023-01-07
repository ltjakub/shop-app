package pl.shop.shopapp.cart;

import lombok.Getter;
import lombok.Setter;
import pl.shop.shopapp.cartItem.CartItem;
import pl.shop.shopapp.client.Client;

import java.util.Set;

@Getter
@Setter
public class CartDto {

    private Long id;
    private Client client;
    private Set<CartItem> cartItems;

}
