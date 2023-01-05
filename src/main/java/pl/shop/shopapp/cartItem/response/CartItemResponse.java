package pl.shop.shopapp.cartItem.response;

import lombok.Getter;
import lombok.Setter;
import pl.shop.shopapp.cartItem.CartItemOutputDto;

import java.util.List;

@Getter
@Setter
public class CartItemResponse {
    private double totalPrice;
    private List<CartItemOutputDto> items;
}
