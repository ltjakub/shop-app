package pl.shop.shopapp.cartItem;

import org.springframework.stereotype.Service;

@Service
public class CartItemDtoOutputMapper {
    CartItemOutputDto map (CartItem item) {
        CartItemOutputDto dto = new CartItemOutputDto();
        dto.setId(item.getId());
        dto.setCartId(item.getCart().getId());
        dto.setProduct(item.getProduct());
        dto.setQuantity(item.getQuantity());
        return dto;
    }
}
