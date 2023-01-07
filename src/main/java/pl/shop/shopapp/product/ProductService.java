package pl.shop.shopapp.product;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static pl.shop.shopapp.utils.JsonConverter.applyPatch;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductDtoMapper mapper;
    private final ProductRepository productRepository;

    public List<ProductDto> findAllProducts() {
        List<ProductDto> productDtos = new ArrayList<>();
        productRepository.findAll().forEach(product -> productDtos.add(mapper.map(product)));
        return productDtos;
    }

    public Page<ProductDto> findProductsWithPagination(int offset, int pageSize) {
        return productRepository.findAll(PageRequest.of(offset, pageSize)).map(mapper::map);
    }

    public Optional<ProductDto> findProductById(Long id) {
        return productRepository.findById(id).map(mapper::map);
    }

    public void addNewProduct(ProductDto productDto) {
        Product product = mapper.map(productDto);
        productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public void updateProduct(ProductDto productDto, JsonMergePatch patch) {
        try {
            ProductDto patchedProduct = applyPatch(productDto, patch);
            Product product = mapper.map(patchedProduct);
            productRepository.save(product);
        } catch (JsonProcessingException | JsonPatchException e) {
            throw new RuntimeException(e);
        }
    }
}
