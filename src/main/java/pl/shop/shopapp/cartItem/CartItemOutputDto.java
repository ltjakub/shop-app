package pl.shop.shopapp.cartItem;

import lombok.Data;
import pl.shop.shopapp.product.Product;
@Data
public class CartItemOutputDto {
    private Long Id;
    private Product product;
    private Long cartId;
    private int quantity;
}
