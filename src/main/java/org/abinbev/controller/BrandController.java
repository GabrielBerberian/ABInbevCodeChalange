package org.abinbev.controller;

import org.abinbev.dto.Brand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for brand
 */
@RestController
@RequestMapping({"v1/brand"})
public class BrandController {

    @GetMapping
    public Brand[] getBrands() {
        return Brand.values();
    }
}
