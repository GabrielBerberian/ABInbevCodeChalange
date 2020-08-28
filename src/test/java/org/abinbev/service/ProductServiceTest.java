package org.abinbev.service;

import org.abinbev.dto.Brand;
import org.abinbev.dto.NewProduct;
import org.abinbev.dto.PersistedProduct;
import org.abinbev.exception.ResourceNotFoundException;
import org.abinbev.model.Product;
import org.abinbev.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    private ProductService productService;
    private ProductRepository productRepository;

    @BeforeEach
    public void setup() {
        productRepository = mock(ProductRepository.class);
        productService = new ProductServiceImpl(productRepository);
    }

    @Test
    public void shouldCreateProduct() {
        when(productRepository.save(any(Product.class))).thenReturn(Product.builder().build());

        PersistedProduct product = productService.createProduct(NewProduct.builder()
                .brand(Brand.AGUILA)
                .description("description")
                .name("name")
                .price(1.23f)
                .build());

        assertNotNull(product);
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    public void shouldUpdateProduct() throws ResourceNotFoundException {
        when(productRepository.findById(anyString())).thenReturn(Optional.of(Product.builder().build()));
        when(productRepository.save(any(Product.class))).thenReturn(Product.builder().build());

        PersistedProduct product = productService.updateProduct(PersistedProduct.builder()
                .id("id")
                .brand(Brand.AGUILA)
                .description("description")
                .name("name")
                .price(1.23f)
                .build());

        assertNotNull(product);
        verify(productRepository, times(1)).findById(anyString());
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    public void shouldNotUpdateProduct() throws ResourceNotFoundException {
        when(productRepository.findById(anyString())).thenReturn(Optional.empty());

        try {
            productService.updateProduct(PersistedProduct.builder()
                    .id("id")
                    .brand(Brand.AGUILA)
                    .description("description")
                    .name("name")
                    .price(1.23f)
                    .build());
        } catch (ResourceNotFoundException e) {
            verify(productRepository, times(1)).findById(anyString());
            verify(productRepository, never()).save(any(Product.class));
        }
    }

    @Test
    public void shouldGetProductById() throws ResourceNotFoundException {
        when(productRepository.findById(anyString())).thenReturn(Optional.of(Product.builder().build()));

        PersistedProduct product = productService.getProductById("id");

        assertNotNull(product);
        verify(productRepository, times(1)).findById(anyString());
    }

    @Test
    public void shouldNotGetProductById() throws ResourceNotFoundException {
        when(productRepository.findById(anyString())).thenReturn(Optional.empty());

        try {
            productService.getProductById("id");
        } catch (ResourceNotFoundException e) {
            verify(productRepository, times(1)).findById(anyString());
        }
    }

    @Test
    public void shouldGetProductsByName() throws ResourceNotFoundException {
        when(productRepository.findAllByName(anyString())).thenReturn(Arrays.asList(Product.builder().build()));

        List<PersistedProduct> products = productService.getProductsByName("name");

        assertFalse(CollectionUtils.isEmpty(products));
        verify(productRepository, times(1)).findAllByName(anyString());
    }

    @Test
    public void shouldNotGetProductsByName() throws ResourceNotFoundException {
        when(productRepository.findAllByName(anyString())).thenReturn(Collections.emptyList());

        try {
            productService.getProductsByName("name");
        } catch (ResourceNotFoundException e) {
            verify(productRepository, times(1)).findAllByName(anyString());
        }
    }

    @Test
    public void shouldGetAllProducts() {
        when(productRepository.findAll()).thenReturn(Arrays.asList(Product.builder().build()));

        List<PersistedProduct> products = productService.getAllProducts();

        assertFalse(CollectionUtils.isEmpty(products));
        verify(productRepository, times(1)).findAll();
    }

    @Test
    public void shouldDeleteProduct() throws ResourceNotFoundException {
        when(productRepository.findById(anyString())).thenReturn(Optional.of(Product.builder().build()));

        PersistedProduct product = productService.deleteProduct("id");

        assertNotNull(product);
        verify(productRepository, times(1)).findById(anyString());
        verify(productRepository, times(1)).deleteById(anyString());
    }

    @Test
    public void shouldNotDeleteProduct() throws ResourceNotFoundException {
        when(productRepository.findById(anyString())).thenReturn(Optional.empty());

        try {
            productService.deleteProduct("id");
        } catch (ResourceNotFoundException e) {
            verify(productRepository, times(1)).findById(anyString());
            verify(productRepository, never()).deleteById(anyString());
        }
    }
}
