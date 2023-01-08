package pl.shop.shopapp.product;

import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;
    private final int MAX_NUMBER_OF_PRODUCTS_ON_PAGE = 3;

    @GetMapping
    public ResponseEntity<List<ProductDto>> findAll() {
        List<ProductDto> products = productService.findAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/page/{offset}")
    public ResponseEntity<Page<ProductDto>> getPaginatedProducts(@PathVariable int offset) {
        Page<ProductDto> productsWithPagination = productService.findProductsWithPagination(offset, MAX_NUMBER_OF_PRODUCTS_ON_PAGE);
        return ResponseEntity.ok(productsWithPagination);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
        ProductDto productDto = productService.findProductById(id);
        return ResponseEntity.ok(productDto);
    }

    @PostMapping
    public void addNewProduct(@Valid @RequestBody ProductDto productDto) {
        productService.addNewProduct(productDto);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getFieldErrors()
                .stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    @PatchMapping("/{id}")
    public void updateProduct(@PathVariable Long id, @RequestBody JsonMergePatch patch) {
        ProductDto productDto = productService.findProductById(id);
        productService.updateProduct(productDto, patch);
    }
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EmptyResultDataAccessException.class)
    private String handleEmptyResultDataAccessException(EmptyResultDataAccessException ex) {
        return ex.getMessage();
    }
}
