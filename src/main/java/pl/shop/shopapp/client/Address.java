package pl.shop.shopapp.client;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Size(min = 1, max = 150)
    private String street;
    @NotNull
    @Size(min = 1, max = 150)
    private String city;
    @NotNull
    @Size(min = 1, max = 150)
    private String state;
    @NotNull
    @Size(min = 1, max = 150)
    private String zip;
}
