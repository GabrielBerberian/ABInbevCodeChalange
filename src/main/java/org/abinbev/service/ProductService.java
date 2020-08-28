package org.abinbev.service;

import org.abinbev.dto.NewProduct;
import org.abinbev.dto.PersistedProduct;
import org.abinbev.exception.ResourceNotFoundException;

import java.util.List;

/**
 * Service for product
 */
public interface ProductService {

    /**
     * Creates product
     *
     * @param newProduct the product to be created
     */
    PersistedProduct createProduct(NewProduct newProduct);

    /**
     * Updates product
     *
     * @param persistedProduct the product to be updated
     */
    PersistedProduct updateProduct(PersistedProduct persistedProduct) throws ResourceNotFoundException;

    /**
     * Gets product by id
     *
     * @param id the product id
     * @return the searched product
     */
    PersistedProduct getProductById(String id) throws ResourceNotFoundException;

    /**
     * Gets products by name
     *
     * @param name the product name
     * @return the searched products
     */
    List<PersistedProduct> getProductsByName(String name) throws ResourceNotFoundException;

    /**
     * Gets all products
     *
     * @return all products
     */
    List<PersistedProduct> getAllProducts();

    /**
     * Deletes product by id
     *
     * @param id the product id
     * @return the deleted product
     */
    PersistedProduct deleteProduct(String id) throws ResourceNotFoundException;
}
