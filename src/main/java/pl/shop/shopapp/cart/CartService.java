package pl.shop.shopapp.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.shop.shopapp.client.Client;
import pl.shop.shopapp.client.ClientRepository;
import pl.shop.shopapp.utils.Error;
import pl.shop.shopapp.utils.exceptions.ResourceNotFoundException;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final ClientRepository clientRepository;

    void createCart(Long clientId) {
            Client client = clientRepository.findById(clientId).orElseThrow(() -> new ResourceNotFoundException(Error.CLIENT_NOT_FOUND.toString()));
            if (!cartRepository.existsByClientId(clientId)) {
                Cart cart = new Cart();
                cart.setClient(client);
                cartRepository.save(cart);
            }
    }
}
