package pl.shop.shopapp.product;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import pl.shop.shopapp.utils.JsonConverter;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ProductRepository repository;


    @Test
    public void shouldReturnAllProducts() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(get("/api/product")).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getContentAsString()).isNotNull();
    }

    @Test
    public void shouldReturn3ProductsOnThePage() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(get("/api/product/page/0")).andReturn().getResponse();
        System.out.println(response.getContentAsString());
        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getContentAsString()).contains("\"id\":1");
        assertThat(response.getContentAsString()).doesNotContain("\"id\":4");
    }

    @Test
    public void shouldReturnProductById() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(get("/api/product/1")).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(200);
    }

    @Test
    public void shouldNOTReturnProductById() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(get("/api/product/100")).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(404);
    }

    @Test
    public void shouldAddNewProduct() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(
                post("/api/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonConverter.convertToJson((new Product(6L, "Shovel", "Dig it", 50.0))))
        ).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(201);
        assertThat(response.getRedirectedUrl()).isEqualTo("http://localhost/api/product/6");
    }

    @Test
    public void shouldDeleteProduct() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(delete("/api/product/4")).andReturn().getResponse();
        Optional<Product> deletedProduct = repository.findById(4L);
        assertThat(deletedProduct.isEmpty()).isTrue();
        assertThat(response.getStatus()).isEqualTo(204);
    }

    @Test
    public void shouldUpdateProductName() throws Exception {
        String newName = "Brush";
        MockHttpServletResponse response = mockMvc.perform(
                        patch("/api/product/2")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"name\":\"" + newName + "\"}")
                )
                .andReturn()
                .getResponse();
        Product product = repository.findById(2L).orElseThrow();
        assertThat(product.getName()).isEqualTo(newName);
        assertThat(response.getStatus()).isEqualTo(204);

    }

}