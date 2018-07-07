package com.nidis.eshop.repositories;

import com.nidis.eshop.models.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    List<Invoice> findAll();
}
