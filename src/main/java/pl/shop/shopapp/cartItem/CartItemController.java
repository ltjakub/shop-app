package pl.shop.shopapp.cartItem;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
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
    public void addProductToTheCart(@Valid @RequestBody CartItemDto cartItemDto) {
        cartItemService.addProductToTheCart(cartItemDto);
    }

    @DeleteMapping("/{id}")
    public void removeProductFromTheCart(@PathVariable Long id) {
        cartItemService.removeProductFromTheCart(id);
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<CartItemResponse> findAllProductsInCart(@PathVariable Long cartId) {
        CartItemResponse allProducts = cartItemService.findAllProductsInTheCart(cartId);
        return ResponseEntity.ok(allProducts);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    private String handleMethodArgumentNotValidException(IllegalArgumentException ex) {
        return ex.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    private Map<String, String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getFieldErrors()
                .stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
    }
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EmptyResultDataAccessException.class)
    private String handleEmptyResultDataAccessException(EmptyResultDataAccessException ex) {
        return ex.getMessage();
    }
}
