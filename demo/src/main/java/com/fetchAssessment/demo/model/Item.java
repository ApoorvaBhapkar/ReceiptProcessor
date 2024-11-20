package com.fetchAssessment.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String shortDescription;
    private double price;

    public Long getId() {
        return id;
    }

    public String getShortDescription() {
        return shortDescription;
    }
    
    public double getPrice() {
        return price;
    }
}
