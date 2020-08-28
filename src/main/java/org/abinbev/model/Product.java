package org.abinbev.model;

import lombok.Builder;
import lombok.Data;
import org.abinbev.dto.Brand;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Model for product
 */
@Data
@Builder
@Document
public class Product {

    @Id
    private String id;
    private String name;
    private String description;
    private float price;
    private Brand brand;
}
