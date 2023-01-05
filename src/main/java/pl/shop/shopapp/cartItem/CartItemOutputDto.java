package pl.shop.shopapp.cartItem;

import lombok.Getter;
import lombok.Setter;
import pl.shop.shopapp.product.Product;

@Getter
@Setter
public class CartItemOutputDto {
    private Long Id;
    private Product product;
    private Long cartId;
    private int quantity;
}
