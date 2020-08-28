package org.abinbev.dto;

import lombok.Builder;
import lombok.Data;
import org.abinbev.model.Product;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

/**
 * Data transfer object for new product
 */
@Data
@Builder
public class NewProduct {

    @NotBlank(message = "name is mandatory")
    private String name;
    private String description;
    @PositiveOrZero(message = "price cannot be negative")
    private Float price;
    private Brand brand;

    public static final NewProduct fromProduct(Product product) {
        return NewProduct.builder()
                .brand(product.getBrand())
                .description(product.getDescription())
                .name(product.getName())
                .price(product.getPrice())
                .build();
    }

    public Product toProduct() {
        return Product.builder()
                .brand(brand)
                .description(description)
                .name(name)
                .price(price)
                .build();
    }
}
