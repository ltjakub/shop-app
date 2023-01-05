package pl.shop.shopapp.product;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductDtoMapper mapper;
    private final ProductRepository productRepository;

    List<ProductDto> findAllProducts() {
        List<ProductDto> productDtos = new ArrayList<>();
        productRepository.findAll().forEach(product -> productDtos.add(mapper.map(product)));
        return productDtos;
    }

    Page<ProductDto> findProductsWithPagination(int offset, int pageSize) {
        return productRepository.findAll(PageRequest.of(offset, pageSize)).map(mapper::map);
    }

    Optional<ProductDto> findProductById(Long id) {
        return productRepository.findById(id).map(mapper::map);
    }

    ProductDto addNewProduct(ProductDto productDto) {
        Product product = mapper.map(productDto);
        Product savedProduct = productRepository.save(product);
        return mapper.map(savedProduct);
    }

    void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    void updateProduct(ProductDto productDto) {
        Product product = mapper.map(productDto);
        productRepository.save(product);
    }

}
