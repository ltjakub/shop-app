package pl.shop.shopapp.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.shop.shopapp.user.User;
import pl.shop.shopapp.user.UserRepository;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CartService {
private final CartRepository cartRepository;
private final UserRepository userRepository;
//private final CartDtoMapper mapper;

    void createCart(Long userId) {
        try {
            User user = userRepository.findById(userId).orElseThrow();
            if (!cartRepository.existsByUserId(userId)) {
            Cart cart = new Cart();
            cart.setUser(user);
            cartRepository.save(cart);
            }
        } catch(NoSuchElementException e) {
            System.err.println("User with provided ID is not found.");
        }
    }
}
