package pl.shop.shopapp.client;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class Client {
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
    @Size(min = 1, max = 50)
    private String phone;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

}