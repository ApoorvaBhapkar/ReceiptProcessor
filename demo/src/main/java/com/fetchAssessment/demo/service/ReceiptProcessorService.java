package com.fetchAssessment.demo.service;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fetchAssessment.demo.dao.ReceiptPointsRepository;
import com.fetchAssessment.demo.dao.ReceiptRepository;
import com.fetchAssessment.demo.model.Item;
import com.fetchAssessment.demo.model.Receipt;
import com.fetchAssessment.demo.model.ReceiptPoints;

import java.util.UUID;
import java.util.regex.Matcher;

@Service
public class ReceiptProcessorService {
    @Autowired
    ReceiptRepository receiptRepository;

    @Autowired
    private ReceiptPointsRepository receiptPointsRepository;

    public ResponseEntity<String> saveReceipt(Receipt receipt) {
        try {
            receiptRepository.save(receipt);
            ReceiptPoints receiptPoints = calculateAndSavePoints(receipt);
            return new ResponseEntity<String>(receiptPoints.getId(),HttpStatus.OK);
        } catch (Exception ex) {
            throw new RuntimeException("Error saving receipt with ID: " + receipt.getId(), ex);
        }
    }

    public ResponseEntity<Integer> getPointsById(String id) {
        try {
            UUID uuid = UUID.fromString(id);
            ReceiptPoints receiptPoints = receiptPointsRepository.findById(uuid.toString())
                    .orElseThrow(() -> new RuntimeException("Receipt points not found for ID: " + id));
            
            return new ResponseEntity<Integer>(receiptPoints.getPoints(),HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            // Handle invalid UUID format
            throw new RuntimeException("Invalid receipt point ID format: " + id, ex);
        }
    }
    

    public ReceiptPoints calculateAndSavePoints(Receipt receipt) {
        try {
            int points = calculatePoints(receipt);
            ReceiptPoints receiptPoints = new ReceiptPoints(receipt.getId(), points);
            receiptPointsRepository.save(receiptPoints);
            return receiptPoints;
        } catch (Exception ex) {
            throw new RuntimeException("Error calculating and saving points for receipt ID: " + receipt.getId(), ex);
        }
    }
    public int calculatePoints(Receipt receipt) {
        try{
            int points = 0;

            // Rule 1: One point for every alphanumeric character in the retailer name.
            points += countAlphanumeric(receipt.getRetailer());

            // Rule 2: 50 points if the total is a round dollar amount with no cents.
            if (receipt.getTotal() == Math.floor(receipt.getTotal())) {
                points += 50;
            }

            // Rule 3: 25 points if the total is a multiple of 0.25.
            if (receipt.getTotal() % 0.25 == 0) {
                points += 25;
            }

            // Rule 4: 5 points for every two items.
            points += (receipt.getItems().size() / 2) * 5;

            // Rule 5: Points based on item description length.
            for (Item item : receipt.getItems()) {
                String description = item.getShortDescription().trim();
                if (description.length() % 3 == 0) {
                    points += Math.ceil(item.getPrice() * 0.2);
                }
            }

            // Rule 6: 6 points if the day in the purchase date is odd.
            String[] dateParts = receipt.getPurchaseDate().split("-");
            int day = Integer.parseInt(dateParts[2]);
            if (day % 2 != 0) {
                points += 6;
            }

            // Rule 7: 10 points if the purchase time is between 2:00pm and 4:00pm.
            String[] timeParts = receipt.getPurchaseTime().split(":");
            int hour = Integer.parseInt(timeParts[0]);
            int minute = Integer.parseInt(timeParts[1]);
            if (hour == 14 || (hour == 15 && minute == 0)) {
                points += 10;
            }
            // receipt.setPoints(points);
            return points;
        }
        catch (Exception ex) {
            throw new RuntimeException("Error calculating points for receipt ID: " + receipt.getId(), ex);
        }
        
    }

    private int countAlphanumeric(String input) {
        try {
            Matcher matcher = Pattern.compile("[a-zA-Z0-9]").matcher(input);
            int count = 0;
            while (matcher.find()) {
                count++;
            }
            return count;
        } catch (Exception ex) {
            throw new RuntimeException("Error counting alphanumeric characters in input: " + input, ex);
        }
    }
}
