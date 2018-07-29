package com.nidis.eshop.repositories;

import com.nidis.eshop.models.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    Optional<CartItem> findByIdAndCartId(Optional<Long> id, Long cartId);

    boolean deleteByCartId(Long cartId);
}
