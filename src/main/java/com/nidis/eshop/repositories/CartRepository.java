package com.nidis.eshop.repositories;

import com.nidis.eshop.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findBySessionIdAndIpAddress(String sessionId, String ipAddress);

    Optional<Cart> findByIdAndSessionIdAndIpAddress(Long cartId, String sessionId, String ipAddress);

    //Cart save(Cart cart);
}
