package com.nidis.eshop.repositories;

import com.nidis.eshop.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session, Long> {
    List<Session> findAll();

    Optional<Session> findById(Long id);

    Optional<Session> findByIdAndIpAddress(Long id, String ipAddress);
}
