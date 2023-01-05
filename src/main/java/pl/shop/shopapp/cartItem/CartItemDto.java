package pl.shop.shopapp.cartItem;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemDto {
    @NotNull
    private Long cartId;
    @NotNull
    private Long productId;
    @NotNull
    @Min(1)
    private int quantity;
}
