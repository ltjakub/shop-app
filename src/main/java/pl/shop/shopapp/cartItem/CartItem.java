package pl.shop.shopapp.cartItem;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.shop.shopapp.cart.Cart;
import pl.shop.shopapp.product.Product;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @ManyToOne
    private Product product;
    @ManyToOne
    private Cart cart;
    private int quantity;
}
