package com.info.ordermangmentsystem.controller;

import com.info.ordermangmentsystem.dto.ReceiptRequestDTO;
import com.info.ordermangmentsystem.dto.ReceiptResponseDTO;
import com.info.ordermangmentsystem.service.ReceiptService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/receipts")
@RequiredArgsConstructor
public class ReceiptController {

    private final ReceiptService receiptService;

    @PostMapping("/generate")
    public ResponseEntity<ReceiptResponseDTO> generateReceipt(
            @RequestBody ReceiptRequestDTO request) {

        ReceiptResponseDTO response =
                receiptService.generateReceipt(request);

        return ResponseEntity.ok(response);
    }
}
