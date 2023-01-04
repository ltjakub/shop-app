package pl.shop.shopapp.cartItem;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import pl.shop.shopapp.cart.Cart;
import pl.shop.shopapp.product.Product;

@Entity
@Data
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
