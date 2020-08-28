package org.abinbev.repository;

import org.abinbev.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/**
 * Repository for product
 */
public interface ProductRepository extends MongoRepository<Product, String> {

    @Query("{ 'name': ?0 }")
    List<Product> findAllByName(String name);
}