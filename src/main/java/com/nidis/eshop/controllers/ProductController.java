package com.nidis.eshop.controllers;

import com.nidis.eshop.models.Product;
import com.nidis.eshop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/products", produces = MediaTypes.HAL_JSON_VALUE)
public class ProductController {

    private  ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public void findProducts() {
        final Iterable<Product> products = productService.findAll();

        ResponseEntity.ok();
    }
}
