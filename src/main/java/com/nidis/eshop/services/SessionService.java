package com.nidis.eshop.services;

import com.nidis.eshop.models.Session;
import com.nidis.eshop.repositories.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SessionService {
    private SessionRepository sessionRepository;

    @Autowired
    public  SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public List<Session> findAll() {
        return sessionRepository.findAll();
    }

    public Optional<Session> findById(Long id) {
        return sessionRepository.findById(id);
    }

    public Optional<Session> findByIdAndIpAddress(Long id, String ipAddress) {
        return  sessionRepository.findByIdAndIpAddress(id, ipAddress);
    }

}
