package com.nidis.eshop.repositories;

import com.nidis.eshop.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findAll();
}
