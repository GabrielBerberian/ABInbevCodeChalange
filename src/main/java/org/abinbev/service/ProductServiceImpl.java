package org.abinbev.service;

import org.abinbev.dto.NewProduct;
import org.abinbev.dto.PersistedProduct;
import org.abinbev.exception.ResourceNotFoundException;
import org.abinbev.model.Product;
import org.abinbev.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of {@link ProductService}
 */
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public PersistedProduct createProduct(NewProduct newProduct) {
        return PersistedProduct.fromProduct(
                productRepository.save(
                        newProduct.toProduct()
                )
        );
    }

    @Override
    public PersistedProduct updateProduct(PersistedProduct persistedProduct) throws ResourceNotFoundException {
        PersistedProduct oldProduct = getProductById(persistedProduct.getId());
        return PersistedProduct.fromProduct(
                productRepository.save(
                        persistedProduct.toProduct()
                )
        );
    }

    @Override
    public PersistedProduct getProductById(String id) throws ResourceNotFoundException {
        return PersistedProduct.fromProduct(
                productRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException(Product.class, "id", id))
        );
    }

    @Override
    public List<PersistedProduct> getProductsByName(String name) throws ResourceNotFoundException {
        List<Product> products = productRepository.findAllByName(name);
        if (CollectionUtils.isEmpty(products)) {
            throw new ResourceNotFoundException(Product.class, "name", name);
        }
        return products.stream()
                .map(PersistedProduct::fromProduct)
                .collect(Collectors.toList());
    }

    @Override
    public List<PersistedProduct> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(PersistedProduct::fromProduct)
                .collect(Collectors.toList());
    }

    @Override
    public PersistedProduct deleteProduct(String id) throws ResourceNotFoundException {
        Product deleteProduct = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Product.class, "id", id));
        productRepository.deleteById(id);
        return PersistedProduct.fromProduct(deleteProduct);
    }
}
