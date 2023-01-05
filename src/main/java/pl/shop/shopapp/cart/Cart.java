package pl.shop.shopapp.cart;

import jakarta.persistence.*;
import lombok.*;
import pl.shop.shopapp.cartItem.CartItem;
import pl.shop.shopapp.user.User;

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
    private User user;

    @OneToMany
    @JoinColumn(name = "cart_id")
    private Set<CartItem> cartItems;

}
