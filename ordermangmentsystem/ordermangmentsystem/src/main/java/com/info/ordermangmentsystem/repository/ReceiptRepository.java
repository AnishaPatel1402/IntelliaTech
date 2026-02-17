package com.info.ordermangmentsystem.repository;

import com.info.ordermangmentsystem.entity.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReceiptRepository extends JpaRepository<Receipt, Long> {

    Optional<Receipt> findByInvoiceId(Integer invoiceId);
}

