package com.nidis.eshop.services;

import com.nidis.eshop.models.Cart;
import com.nidis.eshop.models.CartItem;
import com.nidis.eshop.repositories.CartItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CartItemService {
    private CartItemRepository cartItemRepository;

    @Autowired
    public CartItemService(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    public Optional<CartItem> findById(Long id) {
        return cartItemRepository.findById(id);
    }

    public List<CartItem> findAll() {
        return cartItemRepository.findAll();
    }

    private Optional<CartItem> findByIdAndCartId(Optional<Long> id, Long cartId) {
        return cartItemRepository.findByIdAndCartId(id, cartId);
    }

    public CartItem save(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    public void delete(CartItem cartItem) {
        cartItemRepository.delete(cartItem);
    }

    private void deleteByCartId(Long cartId) {
        cartItemRepository.deleteByCartId(cartId);
    }

    public boolean clearItem(Optional<Long> itemId, Long cartId) {
        Optional<CartItem> cartItem = findByIdAndCartId(itemId, cartId);

        if (cartItem.isPresent()) {
            delete(cartItem.get());
            log.info("item removed");

            return true;
        } else {
            log.info("item not found");

            return false;
        }
    }

    public boolean clearAllItems(Optional<Cart> cart) {
        if (cart.isPresent()) {
            deleteByCartId(cart.get().getId());
            log.info("all items have been removed");

            return true;
        } else {
            log.info("cart is not found");

            return false;
        }
    }
}
