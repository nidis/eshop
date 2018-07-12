package com.nidis.eshop.controllers;

import com.nidis.eshop.assemblers.ProductAssembler;
import com.nidis.eshop.models.Product;
import com.nidis.eshop.resources.ProductResource;
import com.nidis.eshop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/products", produces = MediaTypes.HAL_JSON_VALUE)
public class ProductController {

    private  ProductService productService;
    private ProductAssembler productAssembler;

    @Autowired
    public ProductController(ProductService productService, ProductAssembler productAssembler) {
        this.productService = productService;
        this.productAssembler = productAssembler;
    }

    @GetMapping
    public ResponseEntity<?> findProducts() {
        final List<Product> products = productService.findAll();

        final ProductResource resource = productAssembler.toResource(products);

        return ResponseEntity.ok(products);
    }
}
