package com.nidis.eshop.controllers;

import com.nidis.eshop.models.CartItem;
import com.nidis.eshop.services.CartItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Slf4j
public class CartItemController {
    CartItemService cartItemService;

    @Autowired
    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @DeleteMapping(value = "/item", produces = {"application/hal+json"})    //   /item?cartId=1&productId=2
    public ResponseEntity<?> removeItem(@RequestParam(value = "cartId") Long cartId, @RequestParam(value = "productId") Long productId) {
        Optional<CartItem> cartItem = cartItemService.findByCartIdAndProductId(cartId, productId);

        if(cartItem.isPresent()) {
            log.info("item deleted");
            cartItemService.delete(cartItem.get());
        } else {
            log.info("item not found");
            return ResponseEntity.ok(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(HttpStatus.OK);
    }
}
