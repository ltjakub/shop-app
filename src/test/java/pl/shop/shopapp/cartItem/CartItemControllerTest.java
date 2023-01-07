package pl.shop.shopapp.cartItem;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import pl.shop.shopapp.utils.JsonConverter;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
@RequiredArgsConstructor
class CartItemControllerTest {
    @Autowired
    private CartItemRepository repository;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldAddProductToTheCart() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(
                post("/api/cart")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonConverter.convertToJson((new CartItemDto(2L, 4L, 20))))
        ).andReturn().getResponse();
        List<CartItem> cartItems = repository.findCartItemByCart_Id(2L);
        Long productId = cartItems.get(1).getProduct().getId();
        assertThat(productId).isEqualTo(4L);
        assertThat(response.getStatus()).isEqualTo(200);
    }

    @Test
    public void shouldRemoveItemFromTheCart() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(delete("/api/cart/1")).andReturn().getResponse();
        Optional<CartItem> removedItem = repository.findById(1L);
        assertThat(removedItem.isEmpty()).isTrue();
        assertThat(response.getStatus()).isEqualTo(200);
    }

    @Test
    public void shouldFindAllItemsInTheCart() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(get("/api/cart/2")).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getContentAsString()).isNotNull();
    }
}