package com.nidis.eshop.services;

import com.nidis.eshop.models.Cart;
import com.nidis.eshop.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Cart> findById(Long id) {
        return cartRepository.findById(id);
    }

    public Optional<Cart> findBySessionIdAndIpAddress(String sessionId, String ipAddress) {
        return cartRepository.findBySessionIdAndIpAddress(sessionId, ipAddress);
    }

    public Optional<Cart> findByIdAndSessionIdAndIpAddress(Long cartId, String sessionId, String ipAddress) {
        return cartRepository.findByIdAndSessionIdAndIpAddress(cartId, sessionId, ipAddress);
    }

    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }
}
