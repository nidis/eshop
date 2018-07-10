package com.nidis.eshop.services;

import com.nidis.eshop.models.Cart;
import com.nidis.eshop.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    private CartRepository cartRepository;

    @Autowired
    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public List<Cart> findAll() {
        return cartRepository.findAll();
    }

    public Cart findBySessionId(String uuid) {
        return cartRepository.findBySessionId(uuid);
    }

    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }
}
