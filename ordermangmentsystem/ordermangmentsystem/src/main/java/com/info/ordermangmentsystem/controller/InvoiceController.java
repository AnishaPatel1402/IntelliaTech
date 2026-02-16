package com.info.ordermangmentsystem.controller;

import com.info.ordermangmentsystem.dto.InvoiceResponseDTO;
import com.info.ordermangmentsystem.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/invoices")
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;

    @PostMapping("/generate/{orderId}")
    public ResponseEntity<InvoiceResponseDTO> generateInvoice(
            @PathVariable Integer orderId) {

        InvoiceResponseDTO response =
                invoiceService.generateInvoice(orderId);

        return ResponseEntity.ok(response);
    }
}

