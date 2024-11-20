package com.fetchAssessment.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.fetchAssessment.demo.model.ReceiptPoints;

public interface ReceiptPointsRepository extends JpaRepository<ReceiptPoints, String> {
}
