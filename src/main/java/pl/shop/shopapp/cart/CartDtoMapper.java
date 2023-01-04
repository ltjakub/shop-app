package pl.shop.shopapp.cart;

import org.springframework.stereotype.Service;

@Service
public class CartDtoMapper {
    Cart map(CartDto dto) {
        Cart cart = new Cart();
        cart.setId(dto.getId());
        cart.setUser(dto.getUser());
        cart.setCartItems(dto.getCartItems());
        return cart;
    }
    CartDto map(Cart cart) {
        CartDto dto = new CartDto();
        dto.setId(cart.getId());
        dto.setUser(cart.getUser());
        dto.setCartItems(cart.getCartItems());
    return dto;
    }
}
