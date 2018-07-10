package com.nidis.eshop.controllers;

import com.nidis.eshop.models.Product;
import com.nidis.eshop.services.CartItemService;
import com.nidis.eshop.services.CartService;
import com.nidis.eshop.services.ProductService;
import com.nidis.eshop.utils.CartStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/products", produces = MediaTypes.HAL_JSON_VALUE)
public class ProductController {

    private  ProductService productService;
    private CartService cartService;
    private CartItemService cartItemService;

    @Autowired
    public ProductController(ProductService productService, CartService cartService, CartItemService cartItemService) {
        this.productService = productService;
        this.cartService = cartService;
        this.cartItemService = cartItemService;
    }

    @GetMapping
    public void findProducts() {
        final List<Product> products = productService.findAll();

        products.stream()
                .forEach(product -> product.getId());
        final CartStatus created = CartStatus.CREATED;
        ResponseEntity.ok();
    }


}
