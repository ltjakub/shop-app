package pl.shop.shopapp.cartItem;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import pl.shop.shopapp.cartItem.response.CartItemResponse;

import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cart")
public class CartItemController {
    private final CartItemService cartItemService;

    @PostMapping
    ResponseEntity<?> addProductToTheCart(@Valid @RequestBody CartItemDto cartItemDto) {
        cartItemService.addProductToTheCart(cartItemDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> removeProductFromTheCart(@PathVariable Long id) {
        cartItemService.removeProductFromTheCart(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    ResponseEntity<CartItemResponse> findAllProducts(@PathVariable Long id) {
        CartItemResponse allProducts = cartItemService.findAllProductsInTheCart(id);
        return ResponseEntity.ok(allProducts);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    String handleMethodArgumentNotValidException(IllegalArgumentException ex) {
        return ex.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    Map<String, String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getFieldErrors()
                .stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
    }
}
