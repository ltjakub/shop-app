package pl.shop.shopapp.product;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean(ProductRepository.class)
    ProductRepository repository;
    @MockBean(ProductService.class)
    ProductService service;

    @Test
    public void shouldFindAllProducts() throws Exception {
        Product product1 = new Product(1L, "Book", "Best book in the world", 5.5);
        Product product2 = new Product(2L, "Phone", "Best phone in the world", 500.0);
        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        given(repository.findAll()).willReturn(products);

        MockHttpServletResponse response = mockMvc.perform(get("/api/product")).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getContentAsString()).isNotEmpty();
    }

    @Test
    public void shouldFindProductById() throws Exception {
        Optional<ProductDto> product = Optional.of(new ProductDto(1L, "Book", "Best book in the world", 5.5));
        given(service.findProductById(1L)).willReturn(product);

        MockHttpServletResponse response = mockMvc.perform(get("/api/product/1")).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getContentAsString()).isNotEmpty();
    }

    @Test
    public void shouldNOTFindProductById() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(get("/api/product/1")).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(404);
        assertThat(response.getContentAsString()).isEmpty();
    }

    @Test
    public void shouldDeleteProduct(){
        repository.deleteById(1L);
        Mockito.verify(repository, Mockito.times(1)).deleteById(eq(1L));
    }

}