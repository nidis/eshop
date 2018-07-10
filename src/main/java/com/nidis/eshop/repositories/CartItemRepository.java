package com.nidis.eshop.repositories;

import com.nidis.eshop.models.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findAll();

    CartItem save(CartItem cartItem);
}
