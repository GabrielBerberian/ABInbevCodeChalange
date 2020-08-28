package org.abinbev.controller;

import org.abinbev.dto.NewProduct;
import org.abinbev.dto.PersistedProduct;
import org.abinbev.service.ProductService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller for product
 */
@RestController
@RequestMapping({"v1/product"})
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public PersistedProduct createProduct(@RequestBody @Valid NewProduct newProduct) {
        return productService.createProduct(newProduct);
    }

    @PutMapping
    public PersistedProduct updateProduct(@RequestBody @Valid PersistedProduct persistedProduct) {
        return productService.updateProduct(persistedProduct);
    }

    @GetMapping("/{id}")
    public PersistedProduct getProductById(@PathVariable("id") String id) {
        return productService.getProductById(id);
    }

    @GetMapping
    public List<PersistedProduct> getProductsByName(@RequestParam(required = false) String name) {
        List<PersistedProduct> products = name == null ?
                productService.getAllProducts() : productService.getProductsByName(name);
        return products.stream().sorted(Comparator.comparing(PersistedProduct::getName)).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public PersistedProduct deleteProduct(@PathVariable("id") String id) {
        return productService.deleteProduct(id);
    }

}
