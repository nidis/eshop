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

    public Cart findBySessionIdAndIpAddress(String sessionId, String ipAddress) {
        return cartRepository.findBySessionIdAndIpAddress(sessionId, ipAddress);
    }

    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }
}
