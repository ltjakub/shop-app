package pl.shop.shopapp.cart;

import jakarta.persistence.*;
import lombok.*;
import pl.shop.shopapp.cartItem.CartItem;
import pl.shop.shopapp.client.Client;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Client client;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CartItem> cartItems;

}
