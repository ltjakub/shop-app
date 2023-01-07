package pl.shop.shopapp.cartItem;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.shop.shopapp.cart.Cart;
import pl.shop.shopapp.cart.CartRepository;
import pl.shop.shopapp.cartItem.response.CartItemResponse;
import pl.shop.shopapp.cartItem.response.ResponseMapper;
import pl.shop.shopapp.product.Product;
import pl.shop.shopapp.product.ProductRepository;
import pl.shop.shopapp.utils.Error;
import pl.shop.shopapp.utils.exceptions.ResourceNotFoundException;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CartItemService {
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final CartItemDtoOutputMapper mapper;
    private final ResponseMapper responseMapper;

    void addProductToTheCart(CartItemDto cartItemDto) {
        if (cartItemRepository.countCartItemByCart_Id(cartItemDto.getCartId()) < 3) {
                Product product = productRepository.findById(cartItemDto.getProductId()).orElseThrow(() -> new ResourceNotFoundException(Error.PRODUCT_NOT_FOUND.toString()));
                Cart cart = cartRepository.findById(cartItemDto.getCartId()).orElseThrow(() -> new ResourceNotFoundException(Error.CART_NOT_FOUND.toString()));
                CartItem cartItem = new CartItem();
                cartItem.setProduct(product);
                cartItem.setCart(cart);
                cartItem.setQuantity(cartItemDto.getQuantity());
                cartItemRepository.save(cartItem);
        }

    }

    void removeProductFromTheCart(Long id) {
        cartItemRepository.deleteById(id);
    }

    CartItemResponse findAllProductsInTheCart(Long id) {
        List<CartItemOutputDto> cartItems = cartItemRepository.findCartItemByCart_Id(id)
                .stream()
                .map(mapper::map)
                .toList();
        return responseMapper.map(cartItems);
    }

}
