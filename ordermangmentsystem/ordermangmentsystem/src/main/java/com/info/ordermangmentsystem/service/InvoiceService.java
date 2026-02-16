package com.info.ordermangmentsystem.service;

import com.info.ordermangmentsystem.dto.InvoiceItemDTO;
import com.info.ordermangmentsystem.dto.InvoiceResponseDTO;
import com.info.ordermangmentsystem.entity.Invoice;
import com.info.ordermangmentsystem.entity.InvoiceItem;
import com.info.ordermangmentsystem.entity.Order;
import com.info.ordermangmentsystem.entity.OrderItem;
import com.info.ordermangmentsystem.repository.InvoiceItemRepository;
import com.info.ordermangmentsystem.repository.InvoiceRepository;
import com.info.ordermangmentsystem.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final InvoiceItemRepository invoiceItemRepository;
    private final OrderRepository orderRepository;

    public InvoiceResponseDTO generateInvoice(Integer orderId){
        Optional<Invoice> existingInvoice =
                invoiceRepository.findByOrderId(orderId);

        if (existingInvoice.isPresent()) {
            return buildResponse(existingInvoice.get());
        }


        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        Invoice invoice = new Invoice();

        invoice.setInvoiceNumber(generateInvoiceNumber());
        invoice.setOrderId(order.getId());
        invoice.setUserId(order.getUser().getId());
        invoice.setOrderReference(order.getOrderReference());

        invoice.setCustomerName(order.getUser().getName());
        invoice.setCustomerEmail(order.getUser().getEmail());

        invoice.setInvoiceDate(LocalDateTime.now());
        invoice.setDueDate(LocalDateTime.now().plusDays(15));

        invoice.setSubtotal(order.getTotalAmount());
        invoice.setDiscountAmount(order.getDiscountAmount());

        invoice.setFinalAmount(order.getFinalAmount());

        invoice.setPaymentStatus("PENDING");

        Invoice savedInvoice = invoiceRepository.save(invoice);


        for (OrderItem orderItem : order.getOrderItems()) {

            InvoiceItem invoiceItem = new InvoiceItem();
            invoiceItem.setInvoiceId(savedInvoice.getId());
            invoiceItem.setProductId(orderItem.getProduct().getId());
            invoiceItem.setProductName(orderItem.getProduct().getName());
            invoiceItem.setQuantity(orderItem.getQuantity());
            invoiceItem.setUnitPrice(orderItem.getProduct().getPrice());
            invoiceItem.setTotalPrice(orderItem.getProduct().getPrice() * orderItem.getQuantity());

            invoiceItemRepository.save(invoiceItem);
        }

        return buildResponse(savedInvoice);
    }

    private InvoiceResponseDTO buildResponse(Invoice invoice) {

        InvoiceResponseDTO dto = new InvoiceResponseDTO();

        dto.setInvoiceNumber(invoice.getInvoiceNumber());
        dto.setOrderId(invoice.getOrderId().intValue());
        dto.setOrderReference(invoice.getOrderReference());

        dto.setUserId(invoice.getUserId().intValue());
        dto.setCustomerName(invoice.getCustomerName());
        dto.setCustomerEmail(invoice.getCustomerEmail());

        dto.setInvoiceDate(invoice.getInvoiceDate());
        dto.setDueDate(invoice.getDueDate());

        dto.setSubtotal(invoice.getSubtotal());
        dto.setDiscountAmount(invoice.getDiscountAmount());
        dto.setTaxAmount(invoice.getTaxAmount());
        dto.setFinalAmount(invoice.getFinalAmount());

        dto.setPaymentStatus(invoice.getPaymentStatus());

        // Fetch Invoice Items
        List<InvoiceItem> invoiceItems =
                invoiceItemRepository.findByInvoiceId(invoice.getId());

        List<InvoiceItemDTO> itemDTOList = invoiceItems.stream()
                .map(item -> {
                    InvoiceItemDTO itemDTO = new InvoiceItemDTO();
                    itemDTO.setProductId(item.getProductId().intValue());
                    itemDTO.setProductName(item.getProductName());
                    itemDTO.setQuantity(item.getQuantity());
                    itemDTO.setUnitPrice(item.getUnitPrice());
                    itemDTO.setTotalPrice(item.getTotalPrice());
                    return itemDTO;
                }).toList();

        dto.setItems(itemDTOList);

        return dto;
    }

    private String generateInvoiceNumber() {
        return "INV-" + System.currentTimeMillis();
    }
}
