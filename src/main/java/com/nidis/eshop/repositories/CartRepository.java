package com.nidis.eshop.repositories;

import com.nidis.eshop.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface CartRepository extends JpaRepository<Cart, Long> {

    Cart findBySessionIdAndIpAddress(String sessionId, String ipAddress);

    Cart save(Cart cart);
}
