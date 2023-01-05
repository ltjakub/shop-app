package pl.shop.shopapp.cartItem;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    int countCartItemByCart_Id(Long cartId);

    List<CartItem> findCartItemByCart_Id(Long id);
}
