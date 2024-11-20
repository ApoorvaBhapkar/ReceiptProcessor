package com.fetchAssessment.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.fetchAssessment.demo.model.Receipt;
import com.fetchAssessment.demo.service.ReceiptProcessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("receipts")

public class ReceiptProcessorController {
    @Autowired
    ReceiptProcessorService receiptService;
    
    @PostMapping("process")
    public ResponseEntity<String> saveReceipt(@RequestBody Receipt receipt) {
        return receiptService.saveReceipt(receipt);
    }

    @GetMapping("{id}/points")
    public ResponseEntity<Integer> getReceipt(@PathVariable String id) {
        return receiptService.getPointsById(id);
    }
    
}
