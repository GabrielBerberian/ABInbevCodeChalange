package org.abinbev.dto;

import lombok.Builder;
import lombok.Data;
import org.abinbev.model.Product;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

/**
 * Data transfer object for created product
 */
@Data
@Builder
public class PersistedProduct {

    @NotBlank(message = "id is mandatory")
    private String id;
    @NotBlank(message = "name is mandatory")
    private String name;
    private String description;
    @PositiveOrZero(message = "price cannot be negative")
    private float price;
    private Brand brand;

    public static final PersistedProduct fromProduct(Product product) {
        return PersistedProduct.builder()
                .id(product.getId())
                .brand(product.getBrand())
                .description(product.getDescription())
                .name(product.getName())
                .price(product.getPrice())
                .build();
    }

    public Product toProduct() {
        return Product.builder()
                .id(id)
                .brand(brand)
                .description(description)
                .name(name)
                .price(price)
                .build();
    }
}
