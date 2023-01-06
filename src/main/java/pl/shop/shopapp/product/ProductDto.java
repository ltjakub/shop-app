package pl.shop.shopapp.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Long id;
    @NotNull
    @Size(min = 1, max = 200)
    private String name;
    @NotNull
    @Size(min = 1, max = 1000)
    private String description;
    @NotNull
    private double price;
}
