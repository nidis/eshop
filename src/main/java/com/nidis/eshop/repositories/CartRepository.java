package com.nidis.eshop.repositories;

import com.nidis.eshop.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findAll();

    Optional<Cart> findById(Long id);

    Cart findBySessionIdAndIpAddress(String sessionId, String ipAddress);

    Cart save(Cart cart);
}
