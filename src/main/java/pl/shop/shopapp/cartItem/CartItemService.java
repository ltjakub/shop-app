package pl.shop.shopapp.cartItem;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.shop.shopapp.cart.Cart;
import pl.shop.shopapp.cart.CartRepository;
import pl.shop.shopapp.product.Product;
import pl.shop.shopapp.product.ProductRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CartItemService {
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final CartItemDtoOutputMapper mapper;

    void addProductToTheCart(CartItemDto cartItemDto) {
        if(cartItemRepository.countCartItemByCart_Id(cartItemDto.getCartId()) < 3) {
            try {
                Product product = productRepository.findById(cartItemDto.getProductId()).orElseThrow();
                Cart cart = cartRepository.findById(cartItemDto.getCartId()).orElseThrow();
                CartItem cartItem = new CartItem();
                cartItem.setProduct(product);
                cartItem.setCart(cart);
                cartItem.setQuantity(cartItemDto.getQuantity());
//            if(cartItemRepository.existsByCart_IdAndProduct_Id(cart.getId(), product.getId())) {
//                System.out.println("test");
//            } TBI
                cartItemRepository.save(cartItem);

            } catch(NoSuchElementException e) {
                System.err.println("Product or cart with provided ID doesn't exist.");
            }
        }

    }
    void removeProductFromTheCart(Long id) {
        cartItemRepository.deleteById(id);
    }
    List<CartItemOutputDto> findAllProductsInTheCart() {
        System.out.println(cartItemRepository.findAll());
        List<CartItemOutputDto> cartItems = cartItemRepository.findAll()
                .stream()
                .map(mapper::map)
                .toList();
        return cartItems;
    }


}
