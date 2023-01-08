package pl.shop.shopapp.cartItem;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pl.shop.shopapp.cart.CartRepository;
import pl.shop.shopapp.product.ProductRepository;
import pl.shop.shopapp.utils.exceptions.ResourceNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
class CartItemServiceTest {
    @MockBean
    private ProductRepository productRepository;
    @MockBean
    private CartRepository cartRepository;
    @Autowired
    private CartItemService service;

    @Test
    public void shouldThrowExceptionAfterNotFindingProductById() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> service.addProductToTheCart(new CartItemDto(1L, 1L, 1)));
    }

    @Test
    public void shouldThrowExceptionAfterNotFindingCartById() {
        when(cartRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> service.addProductToTheCart(new CartItemDto(1L, 1L, 1)));;
    }

}