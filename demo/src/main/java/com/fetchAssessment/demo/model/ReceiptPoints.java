package com.fetchAssessment.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import java.util.UUID;
@Entity
@Data
public class ReceiptPoints {
    
    @Id
    @Column(name = "id", columnDefinition = "CHAR(36)")
    private String id = UUID.randomUUID().toString(); 

    private Long receiptId;  // Foreign key linking to the receipt
    private Integer points; 

    public ReceiptPoints() {
    }

    public ReceiptPoints(Long receiptId, Integer points) {
        this.receiptId = receiptId;
        this.points = points;
    }
}
