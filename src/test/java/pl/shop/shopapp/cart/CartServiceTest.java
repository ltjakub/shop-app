package pl.shop.shopapp.cart;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pl.shop.shopapp.client.Client;
import pl.shop.shopapp.client.ClientRepository;
import pl.shop.shopapp.utils.exceptions.ResourceNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@SpringBootTest
class CartServiceTest {
    @MockBean
    private CartRepository cartRepository;
    @MockBean
    private ClientRepository clientRepository;
    @Autowired
    CartService cartService;
    @Test
    public void shouldCreateNewCart() {
        given(clientRepository.findById(anyLong())).willReturn(Optional.of(new Client()));
        cartService.createCart(1L);
        verify(cartRepository, times(1)).save(any());
    }

    @Test
    public void shouldNotCreateNewCart() {
        given(clientRepository.findById(anyLong())).willReturn(Optional.of(new Client()));
        given(cartRepository.existsByClientId(anyLong())).willReturn(true);
        cartService.createCart(1L);
        verify(cartRepository, times(0)).save(any());
    }

    @Test
    public void shouldThrowExceptionAfterNotFindingClientById() {
        when(clientRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> cartService.createCart(1L));
    }
}