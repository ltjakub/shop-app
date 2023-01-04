package pl.shop.shopapp.cartItem;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    @GetMapping
    ResponseEntity<List<CartItemOutputDto>> findAllProducts() {
        List<CartItemOutputDto> allProducts = cartItemService.findAllProductsInTheCart();
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
