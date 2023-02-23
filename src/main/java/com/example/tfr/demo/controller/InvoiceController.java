package com.example.tfr.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.tfr.demo.model.Invoice;
import com.example.tfr.demo.model.Product;
import com.example.tfr.demo.service.InvoiceService;

@RestController
@RequestMapping("/api")
public class InvoiceController {

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostMapping("/invoice")
    public List<Invoice> createInvoices(@RequestBody List<Product> products) {
        return invoiceService.createInvoices(products);
    }
}
