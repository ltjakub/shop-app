package pl.shop.shopapp.cart;

import lombok.Getter;
import lombok.Setter;
import pl.shop.shopapp.cartItem.CartItem;
import pl.shop.shopapp.user.User;

import java.util.Set;

@Getter
@Setter
public class CartDto {

    private Long id;
    private User user;
    private Set<CartItem> cartItems;

}
