package pl.shop.shopapp.cartItem;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import pl.shop.shopapp.cart.Cart;
import pl.shop.shopapp.product.Product;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static pl.shop.shopapp.utils.JsonConverter.convertToJson;

@SpringBootTest
@AutoConfigureMockMvc
@RequiredArgsConstructor
class CartItemControllerTest {
    @MockBean(CartItemRepository.class)
    private CartItemRepository repository;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldRemoveItemFromTheCart() {
        repository.deleteById(1L);
        Mockito.verify(repository, Mockito.times(1)).deleteById(eq(1L));
    }

    @Test
    public void shouldReturnStatus200AfterFindingAllItemsInTheCart() throws Exception {
        CartItem item = new CartItem(1L, new Product(), new Cart(), 5);
        CartItem item2 = new CartItem(1L, new Product(), new Cart(), 4);
        List<CartItem> items = new ArrayList<>();
        items.add(item);
        items.add(item2);
        given(repository.findCartItemByCart_Id(1L)).willReturn(items);
        MockHttpServletResponse response = mockMvc.perform(get("/api/cart/1")).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(200);
    }

    @Test
    public void shouldReturnStatus200AfterNOTFindingAllItemsInTheCart() throws Exception {
        CartItem item = new CartItem(1L, new Product(), new Cart(), 5);
        CartItem item2 = new CartItem(1L, new Product(), new Cart(), 4);
        List<CartItem> items = new ArrayList<>();
        items.add(item);
        items.add(item2);
        given(repository.findCartItemByCart_Id(1L)).willReturn(items);
        MockHttpServletResponse response = mockMvc.perform(get("/api/cart/2")).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(200);
    }

    @Test
    public void shouldAddNewItemToTheCart() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(post("/api/cart")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertToJson(new CartItemDto(1L, 1L, 4))))
                .andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(204);

    }


}