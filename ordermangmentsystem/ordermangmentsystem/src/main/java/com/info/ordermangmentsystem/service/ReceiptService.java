package com.info.ordermangmentsystem.service;

import com.info.ordermangmentsystem.dto.ReceiptRequestDTO;
import com.info.ordermangmentsystem.dto.ReceiptResponseDTO;
import com.info.ordermangmentsystem.entity.Invoice;
import com.info.ordermangmentsystem.entity.Receipt;
import com.info.ordermangmentsystem.repository.InvoiceRepository;
import com.info.ordermangmentsystem.repository.ReceiptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReceiptService {

    private final ReceiptRepository receiptRepository;
    private final InvoiceRepository invoiceRepository;

    @Transactional
    public ReceiptResponseDTO generateReceipt(ReceiptRequestDTO request) {

        Optional<Receipt> existingReceipt =
                receiptRepository.findByInvoiceId(request.getInvoiceId());

        if (existingReceipt.isPresent()) {
            return buildResponse(existingReceipt.get());
        }


        Invoice invoice = invoiceRepository.findById(Long.valueOf(request.getInvoiceId()))
                .orElseThrow(() -> new RuntimeException("Invoice not found"));


        if (!"PENDING".equals(invoice.getPaymentStatus())) {
            throw new RuntimeException("Invoice already paid or invalid state");
        }

        Receipt receipt = new Receipt();
        receipt.setReceiptNumber(generateReceiptNumber());
        receipt.setInvoiceId(invoice.getId());
        receipt.setInvoiceNumber(invoice.getInvoiceNumber());
        receipt.setOrderId(invoice.getOrderId());
        receipt.setUserId(invoice.getUserId());

        receipt.setTransactionId(request.getTransactionId());
        receipt.setPaymentMethod(request.getPaymentMethod());
        receipt.setPaymentDate(LocalDateTime.now());

        receipt.setPaidAmount(invoice.getFinalAmount());
        receipt.setPaymentStatus("SUCCESS");

        Receipt savedReceipt = receiptRepository.save(receipt);


        invoice.setPaymentStatus("PAID");
        invoiceRepository.save(invoice);

        return buildResponse(savedReceipt);
    }

    private ReceiptResponseDTO buildResponse(Receipt receipt) {

        ReceiptResponseDTO dto = new ReceiptResponseDTO();

        dto.setReceiptNumber(receipt.getReceiptNumber());
        dto.setOrderId(receipt.getOrderId());
        dto.setInvoiceNumber(receipt.getInvoiceNumber());
        dto.setTransactionId(receipt.getTransactionId());
        dto.setPaymentMethod(receipt.getPaymentMethod());
        dto.setPaymentDate(receipt.getPaymentDate());
        dto.setPaidAmount(receipt.getPaidAmount());
        dto.setPaymentStatus(receipt.getPaymentStatus());

        return dto;
    }
    private String generateReceiptNumber() {
        return "RCPT-" + System.currentTimeMillis();
    }

}