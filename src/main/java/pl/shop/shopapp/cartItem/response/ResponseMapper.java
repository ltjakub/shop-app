package pl.shop.shopapp.cartItem.response;

import org.springframework.stereotype.Service;
import pl.shop.shopapp.cartItem.CartItemOutputDto;

import java.util.List;

@Service
public class ResponseMapper {
    public CartItemResponse map(List<CartItemOutputDto> items) {
        Double totalPrice = items
                .stream()
                .map(t -> t.getProduct().getPrice() * t.getQuantity())
                .reduce(0.0, Double::sum);
        CartItemResponse response = new CartItemResponse();
        response.setItems(items);
        response.setTotalPrice(totalPrice);
        return response;
    }
}
