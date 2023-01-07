package pl.shop.shopapp.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/createcart")
public class CartController {
    private final CartService cartService;

    @PostMapping("/{clientId}")
    public void createCart(@PathVariable Long clientId) {
        cartService.createCart(clientId);
    }
}
