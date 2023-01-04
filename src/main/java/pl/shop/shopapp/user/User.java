package pl.shop.shopapp.user;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import pl.shop.shopapp.cart.Cart;

@Entity
@Getter
@Setter

@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Size(min = 1, max = 100)
    private String firstName;
    @NotNull
    @Size(min = 1, max = 100)
    private String lastName;
    @NotNull
    @Size(min = 1, max = 150)
    @Email
    private String email;
    @NotNull
    @Size(min = 1, max = 300)
    private String password;

}