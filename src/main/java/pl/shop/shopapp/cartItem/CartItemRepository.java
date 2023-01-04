package pl.shop.shopapp.cartItem;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    boolean existsByCart_IdAndProduct_Id(Long cartId, Long productId);
    int countCartItemByCart_Id(Long cartId);
}
