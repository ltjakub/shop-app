package pl.shop.shopapp.cart;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
class CartControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CartRepository repository;

    @Test
    public void shouldCreateCart() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(post("/api/createcart/3"))
                .andReturn()
                .getResponse();
        boolean isCreated = repository.findById(3L).isPresent();
        assertThat(isCreated).isTrue();
        assertThat(response.getStatus()).isEqualTo(200);
    }
}