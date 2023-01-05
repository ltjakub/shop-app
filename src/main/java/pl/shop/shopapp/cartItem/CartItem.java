package pl.shop.shopapp.cartItem;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pl.shop.shopapp.cart.Cart;
import pl.shop.shopapp.product.Product;

@Entity
@Getter
@Setter
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
