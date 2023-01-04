package pl.shop.shopapp.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/createcart")
public class CartController {
    private final CartService cartService;
    @PostMapping( "/{userId}")
    ResponseEntity<?> createCart(@PathVariable Long userId) {
        cartService.createCart(userId);
        return ResponseEntity.noContent().build();
    }


}
