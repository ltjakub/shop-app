package pl.shop.shopapp.product;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pl.shop.shopapp.utils.exceptions.ResourceNotFoundException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProductServiceTest {
    @MockBean
    private ProductRepository repository;

    @Autowired
    private ProductService service;

    @Test
    public void shouldFindProductById() {
        Product product = new Product(1L, "name", "description", 5.0);
        given(repository.findById(1L)).willReturn(Optional.of(product));
        ProductDto foundProduct = service.findProductById(1L);
        assertThat(foundProduct.getName()).isEqualTo("name");
        verify(repository, times(1)).findById(any());
    }

    @Test
    public void shouldThrowExceptionAfterNotFindingProductById() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> service.findProductById(10L));
    }

}