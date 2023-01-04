package pl.shop.shopapp.product;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;
    private final ObjectMapper objectMapper;
    private final int MAX_NUMBER_OF_PRODUCTS_ON_PAGE = 3;


    @GetMapping
    ResponseEntity<List<ProductDto>> findAll() {
        List<ProductDto> products = productService.findAllProducts();
        return ResponseEntity.ok(products);
    }
    @GetMapping("/page/{offset}")
    ResponseEntity<Page<ProductDto>> getPaginatedProducts (@PathVariable int offset) {
        Page<ProductDto> productsWithPagination = productService.findProductsWithPagination(offset, MAX_NUMBER_OF_PRODUCTS_ON_PAGE);
        return ResponseEntity.ok(productsWithPagination);
    }
    @GetMapping("/{id}")
    ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
        return productService.findProductById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    ResponseEntity<ProductDto> addNewProduct(@Valid @RequestBody ProductDto productDto) {
        ProductDto savedProductDto = productService.addNewProduct(productDto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedProductDto.getId())
                .toUri();
        return ResponseEntity.created(uri).body(savedProductDto);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    Map<String, String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getFieldErrors()
                .stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
    }
    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
    @PatchMapping("/{id}")
    ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody JsonMergePatch patch) {
        try {
        ProductDto productDto = productService.findProductById(id).orElseThrow();
            ProductDto patchedProduct = applyPatch(productDto, patch);
            productService.updateProduct(patchedProduct);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    private ProductDto applyPatch(ProductDto productDto, JsonMergePatch patch) throws JsonProcessingException, JsonPatchException {
        JsonNode jsonProduct = objectMapper.valueToTree(productDto);
        JsonNode patchedJsonProduct = patch.apply(jsonProduct);
        return objectMapper.treeToValue(patchedJsonProduct, ProductDto.class);
    }
}
