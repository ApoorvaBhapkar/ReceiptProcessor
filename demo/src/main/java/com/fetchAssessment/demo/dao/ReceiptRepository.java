package com.fetchAssessment.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.fetchAssessment.demo.model.Receipt;

public interface ReceiptRepository extends JpaRepository<Receipt, Long> {
}

