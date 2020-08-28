package org.abinbev.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.abinbev.dto.Brand;
import org.abinbev.dto.NewProduct;
import org.abinbev.dto.PersistedProduct;
import org.abinbev.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ProductController.class)
@AutoConfigureMockMvc(addFilters = false)
public class ProductControllerTest {

    private static final String URL_TEMPLATE = "/v1/product";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProductService productService;

    @Test
    public void shouldCreateProduct() throws Exception {
        PersistedProduct persistedProduct = PersistedProduct.builder()
                .id("id")
                .brand(Brand.AGUILA)
                .description("description")
                .name("name")
                .price(1.23f)
                .build();
        when(productService.createProduct(any(NewProduct.class))).thenReturn(persistedProduct);

        NewProduct newProduct = NewProduct.builder()
                .brand(Brand.AGUILA)
                .description("description")
                .name("name")
                .price(1.23f)
                .build();
        mockMvc.perform(post(URL_TEMPLATE)
                .content(objectMapper.writeValueAsString(newProduct))
                .contentType("application/json"))
                .andExpect(status().is(HttpStatus.OK.value()));

        verify(productService, times(1)).createProduct(any(NewProduct.class));
    }

    @Test
    public void shouldNotCreateProductInvalidPrice() throws Exception {
        PersistedProduct persistedProduct = PersistedProduct.builder()
                .id("id")
                .brand(Brand.AGUILA)
                .description("description")
                .name("name")
                .price(1.23f)
                .build();
        when(productService.createProduct(any(NewProduct.class))).thenReturn(persistedProduct);

        NewProduct newInvalidProduct = NewProduct.builder()
                .brand(Brand.AGUILA)
                .description("description")
                .name("name")
                .price(-1.23f)
                .build();
        mockMvc.perform(post(URL_TEMPLATE)
                .content(objectMapper.writeValueAsString(newInvalidProduct))
                .contentType("application/json"))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));

        verify(productService, never()).createProduct(any(NewProduct.class));
    }

    @Test
    public void shouldNotCreateProductInvalidName() throws Exception {
        PersistedProduct persistedProduct = PersistedProduct.builder()
                .id("id")
                .brand(Brand.AGUILA)
                .description("description")
                .name("name")
                .price(1.23f)
                .build();
        when(productService.createProduct(any(NewProduct.class))).thenReturn(persistedProduct);

        NewProduct newInvalidProduct = NewProduct.builder()
                .brand(Brand.AGUILA)
                .description("description")
                .name(" ")
                .price(1.23f)
                .build();
        mockMvc.perform(post(URL_TEMPLATE)
                .content(objectMapper.writeValueAsString(newInvalidProduct))
                .contentType("application/json"))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));

        verify(productService, never()).createProduct(any(NewProduct.class));
    }

    @Test
    public void shouldUpdateProduct() throws Exception {
        PersistedProduct persistedProduct = PersistedProduct.builder()
                .id("id")
                .brand(Brand.AGUILA)
                .description("description")
                .name("name")
                .price(1.23f)
                .build();
        when(productService.updateProduct(any(PersistedProduct.class))).thenReturn(persistedProduct);

        PersistedProduct product = PersistedProduct.builder()
                .id("id")
                .brand(Brand.AGUILA)
                .description("description")
                .name("name")
                .price(1.23f)
                .build();
        mockMvc.perform(put(URL_TEMPLATE)
                .content(objectMapper.writeValueAsString(product))
                .contentType("application/json"))
                .andExpect(status().is(HttpStatus.OK.value()));

        verify(productService, times(1)).updateProduct(any(PersistedProduct.class));
    }

    @Test
    public void shouldNotUpdateProductInvalidId() throws Exception {
        PersistedProduct persistedProduct = PersistedProduct.builder()
                .id("id")
                .brand(Brand.AGUILA)
                .description("description")
                .name("name")
                .price(1.23f)
                .build();
        when(productService.updateProduct(any(PersistedProduct.class))).thenReturn(persistedProduct);

        PersistedProduct product = PersistedProduct.builder()
                .brand(Brand.AGUILA)
                .description("description")
                .name("name")
                .price(1.23f)
                .build();
        mockMvc.perform(put(URL_TEMPLATE)
                .content(objectMapper.writeValueAsString(product))
                .contentType("application/json"))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));

        verify(productService, never()).updateProduct(any(PersistedProduct.class));
    }

    @Test
    public void shouldNotUpdateProductInvalidName() throws Exception {
        PersistedProduct persistedProduct = PersistedProduct.builder()
                .id("id")
                .brand(Brand.AGUILA)
                .description("description")
                .name("name")
                .price(1.23f)
                .build();
        when(productService.updateProduct(any(PersistedProduct.class))).thenReturn(persistedProduct);

        PersistedProduct product = PersistedProduct.builder()
                .id("id")
                .brand(Brand.AGUILA)
                .description("description")
                .name(" ")
                .price(1.23f)
                .build();
        mockMvc.perform(put(URL_TEMPLATE)
                .content(objectMapper.writeValueAsString(product))
                .contentType("application/json"))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));

        verify(productService, never()).updateProduct(any(PersistedProduct.class));
    }

    @Test
    public void shouldNotUpdateProductInvalidPrice() throws Exception {
        PersistedProduct persistedProduct = PersistedProduct.builder()
                .id("id")
                .brand(Brand.AGUILA)
                .description("description")
                .name("name")
                .price(1.23f)
                .build();
        when(productService.updateProduct(any(PersistedProduct.class))).thenReturn(persistedProduct);

        PersistedProduct product = PersistedProduct.builder()
                .id("id")
                .brand(Brand.AGUILA)
                .description("description")
                .name("name")
                .price(-1.23f)
                .build();
        mockMvc.perform(put(URL_TEMPLATE)
                .content(objectMapper.writeValueAsString(product))
                .contentType("application/json"))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));

        verify(productService, never()).updateProduct(any(PersistedProduct.class));
    }

    @Test
    public void shouldGetProductById() throws Exception {
        PersistedProduct persistedProduct = PersistedProduct.builder()
                .id("id")
                .brand(Brand.AGUILA)
                .description("description")
                .name("name")
                .price(1.23f)
                .build();
        when(productService.getProductById(anyString())).thenReturn(persistedProduct);

        mockMvc.perform(get(URL_TEMPLATE + "/{id}", "id")
                .contentType("application/json"))
                .andExpect(status().is(HttpStatus.OK.value()));

        verify(productService, times(1)).getProductById(anyString());
    }

    @Test
    public void shouldGetProductsByName() throws Exception {
        PersistedProduct persistedProduct = PersistedProduct.builder()
                .id("id")
                .brand(Brand.AGUILA)
                .description("description")
                .name("name")
                .price(1.23f)
                .build();
        when(productService.getProductsByName(anyString())).thenReturn(Arrays.asList(persistedProduct));

        mockMvc.perform(get(URL_TEMPLATE)
                .param("name", "name")
                .contentType("application/json"))
                .andExpect(status().is(HttpStatus.OK.value()));

        verify(productService, times(1)).getProductsByName(anyString());
    }

    @Test
    public void shouldGetAllProducts() throws Exception {
        PersistedProduct persistedProduct = PersistedProduct.builder()
                .id("id")
                .brand(Brand.AGUILA)
                .description("description")
                .name("name")
                .price(1.23f)
                .build();
        when(productService.getAllProducts()).thenReturn(Arrays.asList(persistedProduct));

        mockMvc.perform(get(URL_TEMPLATE)
                .contentType("application/json"))
                .andExpect(status().is(HttpStatus.OK.value()));

        verify(productService, times(1)).getAllProducts();
    }

    @Test
    public void shouldDeleteProduct() throws Exception {
        PersistedProduct persistedProduct = PersistedProduct.builder()
                .id("id")
                .brand(Brand.AGUILA)
                .description("description")
                .name("name")
                .price(1.23f)
                .build();
        when(productService.getAllProducts()).thenReturn(Arrays.asList(persistedProduct));

        mockMvc.perform(delete(URL_TEMPLATE + "/{id}", "id")
                .contentType("application/json"))
                .andExpect(status().is(HttpStatus.OK.value()));

        verify(productService, times(1)).deleteProduct(anyString());
    }
}
